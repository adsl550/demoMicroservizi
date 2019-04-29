package com.io.giulio.daos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.io.giulio.model.User;

public interface UserDao extends JpaRepository<User, String> {
		
	Optional<User> findById(String id);

}
