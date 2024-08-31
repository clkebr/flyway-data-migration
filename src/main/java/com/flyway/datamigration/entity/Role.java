package com.flyway.datamigration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role extends BaseEntity{

	@Column(nullable = false, unique = true, length = 50)
	private String roleName;

	@Column(length = 200)
	private String description;

	@OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
	private Set<User> users;

}
