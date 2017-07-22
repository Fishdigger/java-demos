package edu.mga.itec4269.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mga.itec4269.model.User;
import edu.mga.itec4269.repository.UserRepository;

@Service("userService")
public class UserService {
	private UserRepository repo;
	
	@Autowired
	public UserService(UserRepository userRepository){
		this.repo = userRepository;
	}
	
	public User findByEmail(String email){
		return repo.findByEmail(email);
	}
	
	public User findByConfirmationToken(String token){
		return repo.findByConfirmationToken(token);
	}
	
	public void saveUser(User user){
		repo.save(user);
	}
}
