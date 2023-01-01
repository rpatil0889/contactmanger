package com.contactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.contactmanager.entities.User;
import com.contactmanager.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByEmail(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not found User");
			
		}
		
		UserDetailsImpl detailsImpl = new UserDetailsImpl(user);
		
		
		return detailsImpl;
	}

}
