package com.habit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.habit.entities.User;
import com.habit.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		User user = userRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found with username: "+username));
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
		builder.password(user.getPassword());
		builder.roles("User");
		return builder.build();
		
	}
}
