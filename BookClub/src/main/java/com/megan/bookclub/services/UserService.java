// pair programmed with Jacob

package com.megan.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.megan.bookclub.models.LoginUser;
import com.megan.bookclub.models.User;
import com.megan.bookclub.repositories.UserRepository;



@Service
public class UserService {

	@Autowired
	// CONNECTS THE SERVICE TO THE REPOSITORY
	private UserRepository userRepo;

	// EMPTY CONSTRUCTOR
	public UserService() {
	}

	// Method to hash password + save user
	public User registerUser(User registeringUser) {
		String hashed = BCrypt.hashpw(registeringUser.getPassword(), BCrypt.gensalt());
		registeringUser.setPassword(hashed);
		return userRepo.save(registeringUser);
	}

	// find user by email
	public User getUser(String email) {
		Optional<User> user = userRepo.findByEmail(email);
		return user.isPresent() ? user.get() : null;
	}

	// get user by id
	public User getUser(Long id) {
		Optional<User> user = userRepo.findById(id);
		return user.isPresent() ? user.get() : null;
	}

	// login 
	public User login(LoginUser loginUser, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}

		User existingUser = getUser(loginUser.getEmail());
		if (existingUser == null) {
			result.rejectValue("email", "Unique", "Unknown email");
			return null;
		}
		if (!BCrypt.checkpw(loginUser.getPassword(), existingUser.getPassword())) {
			result.rejectValue("password", "Matches", "Incorrect password");
			return null;
		}
		return existingUser;
	}

}
