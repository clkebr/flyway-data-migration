package com.flyway.datamigration.repository;

import com.flyway.datamigration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findUsersByUserName(String userName);

	Optional<User> findUserByEmail(String email);

}
