package com.skoneczny.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skoneczny.entites.Role;
import com.skoneczny.entites.User;
import com.skoneczny.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);		
	}

	public void createAdmin(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);		
	}
	
	public User findOne(String email) {
		return userRepository.findById(email).get();		
	}

	public boolean isUserPresent(String email) {		
		if(userRepository.existsById(email)) return true;
		return false;
	}

	public List<User> findAll() {		
		return userRepository.findAll();
	}

	public List<User> findByName(String name) {
//		List<String> namesList = new ArrayList<>(Arrays.asList(name.split(" ")));
//		userRepository.findAll().forEach(x -> x.getName());
		return userRepository.findByNameLike("%"+name+"%");
	}
}
