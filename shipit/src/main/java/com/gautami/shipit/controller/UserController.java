package com.gautami.shipit.controller;


import com.gautami.shipit.dto.UserRequest;
import com.gautami.shipit.model.User;
import com.gautami.shipit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerUser(@RequestBody UserRequest userRequest) {
		userService.createUser(userRequest);
	}

	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public User getUserById(@PathVariable Long id){
		return userService.getUserById(id);
	}

	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public void updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){
		 userService.updateUser(id,userRequest);
	}

	@DeleteMapping("/delete/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}





}
