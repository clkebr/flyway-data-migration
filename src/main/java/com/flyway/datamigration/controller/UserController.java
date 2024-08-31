package com.flyway.datamigration.controller;

import com.flyway.datamigration.entity.User;
import com.flyway.datamigration.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userservice;

	public UserController(UserService userservice) {
		this.userservice = userservice;
	}

	@GetMapping
	public List<User> getAllUsers(){
		return userservice.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id){
		User user = userservice.findById(id);
		if(user != null) return ResponseEntity.ok(user);
		else return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		try {
			User createdUser = userservice.save(user);
			return ResponseEntity.ok(createdUser);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userservice.deleteById(id);
		return ResponseEntity.noContent().build();
	}


}
