package com.kagankuscu.webchat.service;

import com.kagankuscu.webchat.common.IdValidator;
import com.kagankuscu.webchat.model.domain.UserDTO;
import com.kagankuscu.webchat.model.viewModel.UserAddVM;
import com.kagankuscu.webchat.model.viewModel.UserUpdateVM;
import com.kagankuscu.webchat.model.viewModel.UserVM;
import com.kagankuscu.webchat.subStructure.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * userService
 */
@Service
public class UserService implements IBaseService<UserVM, UserAddVM, UserUpdateVM, UserDTO>, IdValidator {

    private final IUserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserService(IUserRepository repository, @Lazy PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<List<UserVM>> get() {
        List<UserDTO> records = repository.findAll();
        List<UserVM> results = new ArrayList<>();

        for (UserDTO record : records) {
            UserVM vm = new UserVM();
            vm.id = record.getId();
            vm.username = record.getUsername();
            vm.password = record.getPassword();

            results.add(vm);
        }

        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    public ResponseEntity<UserVM> getById(Integer id) {
        UserDTO record = null;
        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            record = repository.getOne(id);

            UserVM vm = new UserVM();

            vm.username = record.getUsername();
            vm.id = record.getId();
            vm.password = record.getPassword();

            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<UserDTO> add(UserAddVM model) {

        UserDTO userDTO = repository.findByUsername(model.username);
        if (userDTO != null && model.username.equals(userDTO.getUsername())) {
            return new ResponseEntity<>(userDTO, HttpStatus.FORBIDDEN);
        }

        UserDTO entity = new UserDTO();

        entity.setUsername(model.username);
        entity.setPassword(passwordEncoder.encode(model.password));
        repository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);

    }

    public ResponseEntity<UserDTO> update(Integer id, UserUpdateVM model) {
        UserDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setUsername(model.username);
            entity.setPassword(model.password);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<UserVM> delete(Integer id) {
        if (isValidId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<UserVM>> getIsDeleted(boolean showIsDeleted) {
        List<UserDTO> records = repository.findAll();
        List<UserVM> results = new ArrayList<>();

        for (UserDTO record : records) {
            UserVM vm = new UserVM();
            vm.username = record.getUsername();
            vm.id = record.getId();

            results.add(vm);
        }

        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    public UserDTO getByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public boolean isValidId(Integer id) {
        return id == 0 || id < 0;
    }
}