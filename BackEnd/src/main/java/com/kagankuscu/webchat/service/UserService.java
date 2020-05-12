package com.kagankuscu.webchat.service;

import com.kagankuscu.webchat.common.IdValidator;
import com.kagankuscu.webchat.model.domain.UserDTO;
import com.kagankuscu.webchat.model.viewModel.UserAddVM;
import com.kagankuscu.webchat.model.viewModel.UserUpdateVM;
import com.kagankuscu.webchat.model.viewModel.UserVM;
import com.kagankuscu.webchat.subStructure.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * userService
 */
@Service
public class UserService implements IBaseService<UserVM, UserAddVM, UserUpdateVM, UserDTO>, IdValidator {

    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<UserVM>> get() {
        List<UserDTO> records = repository.findAll();
        List<UserVM> results = new ArrayList<>();

        for (UserDTO record : records) {
            UserVM vm = new UserVM();
            vm.id = record.getId();
            vm.userName = record.getUserName();
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

            vm.userName = record.getUserName();
            vm.id = record.getId();
            vm.password = record.getPassword();

            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<UserDTO> add(UserAddVM model) {
        UserDTO entity = new UserDTO();

        entity.setUserName(model.userName);
        entity.setPassword(model.password);
        repository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);

    }

    public ResponseEntity<UserDTO> update(Integer id, UserUpdateVM model) {
        UserDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setUserName(model.userName);
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
            vm.userName = record.getUserName();
            vm.id = record.getId();

            results.add(vm);
        }

        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public boolean isValidId(Integer id) {
        return id == 0 || id < 0;
    }
}