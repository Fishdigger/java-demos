package edu.mga.itec4269.repository;

import edu.mga.itec4269.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	User findByConfirmationToken(String token);
}
