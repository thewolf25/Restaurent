package org.restaurant.repository;

import java.util.Optional;

import org.restaurant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUserName(String UserName);
}
