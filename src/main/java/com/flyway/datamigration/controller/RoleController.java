package com.flyway.datamigration.controller;

import com.flyway.datamigration.entity.Role;
import com.flyway.datamigration.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/roles")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}


	@GetMapping()
	public List<Role> getAllUsers(){
		return roleService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Role> getUser(@PathVariable Long id){
		Role role = roleService.findById(id);
		if (role != null) return ResponseEntity.ok(role);
		else return ResponseEntity.notFound().build();
	}

	@PostMapping()
	public Role createRole(@RequestBody Role role){
		return roleService.save(role);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
