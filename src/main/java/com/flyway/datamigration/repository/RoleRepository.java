package com.flyway.datamigration.repository;

import com.flyway.datamigration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
