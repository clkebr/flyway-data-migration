package com.flyway.datamigration.repository;

import com.flyway.datamigration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
