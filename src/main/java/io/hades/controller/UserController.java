package io.hades.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hades.dto.Role;
import io.hades.dto.User;
import io.hades.services.interfaces.IUserServices;

/**
 * CRUD operation MS
 */

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserServices userServices;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return new ResponseEntity<>(userServices.getUserById(id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/uname/{userName}", name="userName")
	public ResponseEntity<User> getUserById(@PathVariable String userName) {
		return new ResponseEntity<>(userServices.getUserByUserName(userName),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<>(userServices.createUser(user), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userServices.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
		return new ResponseEntity<>(userServices.updateUserById(id,user), HttpStatus.OK);
	}
	
	//only for test
	@GetMapping("/{id}/role/{roleId}")
	public ResponseEntity<Role> getRoleById(@PathVariable Long id,@PathVariable Long roleId){
		return new ResponseEntity<>(userServices.getRoleById(roleId),HttpStatus.OK);
	}
	
}
