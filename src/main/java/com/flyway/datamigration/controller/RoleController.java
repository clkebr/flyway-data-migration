package com.flyway.datamigration.controller;

import com.flyway.datamigration.entity.Role;
import com.flyway.datamigration.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}


	@GetMapping()
	public List<Role> getAllRoles(){
		return roleService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> getRole(@PathVariable Long id){
		Role role = roleService.findById(id);
		if (role != null) return ResponseEntity.ok(role);
		else return ResponseEntity.notFound().build();
	}

	@PostMapping()
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		try {
			Role createdRole = roleService.save(role);
			return ResponseEntity.ok(createdRole);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
