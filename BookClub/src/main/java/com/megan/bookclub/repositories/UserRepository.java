package com.megan.bookclub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.megan.bookclub.models.User;

@Repository

// creates a find by email method for users
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}