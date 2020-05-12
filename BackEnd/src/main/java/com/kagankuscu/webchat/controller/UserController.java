package com.kagankuscu.webchat.controller;

import com.kagankuscu.webchat.model.domain.UserDTO;
import com.kagankuscu.webchat.model.viewModel.UserAddVM;
import com.kagankuscu.webchat.model.viewModel.UserUpdateVM;
import com.kagankuscu.webchat.model.viewModel.UserVM;
import com.kagankuscu.webchat.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<UserDTO> add(@RequestBody @Valid UserAddVM userAddVM) {
		return service.add(userAddVM);
	}

	@GetMapping
	public ResponseEntity<List<UserVM>> get() {
		return service.get();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<UserVM> getById(@PathVariable Integer id) {
		return service.getById(id);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody @Valid UserUpdateVM userUpdateVM) {
		return service.update(id,userUpdateVM);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<UserVM> delete(@PathVariable Integer id) {
		return service.delete(id);
	}
}
