package com.contactmanager.entities;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name is required !!!")
	@Size(min = 2, message = "Name contain atleast 2 charachter")
	private String name;
	@Column(unique = true)
	@NotBlank(message = "Email is required !!!")
	@Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "Invalid email")
	private String email;
	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp = "^[\\d]{10}$", message = "Only 10 digit Numbers are allowed ")
	private String mobile;
	
	@Column(length = 1000)
	@Size(min = 6, message = "Password lenth Should be 6 to 12 ")
	@Pattern(regexp = "^.*(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message = "Password should contain a-z,A-Z,0-9,Special Character")
	private String password;
	private String role;
	private String imageURL;
	private boolean enabled;
	private String about;
	@Transient
	@AssertTrue(message = "Terms and Conditions must be accepted")
	private boolean agreement;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Contact> contacts;	
}
