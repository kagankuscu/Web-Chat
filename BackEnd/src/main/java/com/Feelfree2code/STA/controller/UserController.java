package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.UserDTO;
import com.Feelfree2code.STA.model.viewModel.UserAddVM;
import com.Feelfree2code.STA.model.viewModel.UserUpdateVM;
import com.Feelfree2code.STA.model.viewModel.UserVM;
import com.Feelfree2code.STA.service.UserService;
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
