package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.IdValidator;
import com.Feelfree2code.STA.model.domain.CustomerDTO;
import com.Feelfree2code.STA.model.viewModel.CustomerAddVM;
import com.Feelfree2code.STA.model.viewModel.CustomerUpdateVM;
import com.Feelfree2code.STA.model.viewModel.CustomerVM;
import com.Feelfree2code.STA.subStructure.ICustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements IBaseService<CustomerVM, CustomerAddVM, CustomerUpdateVM, CustomerDTO>, IdValidator {

    private ICustomerRepository repository;

    public CustomerService(ICustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<CustomerVM>> get() {
        List<CustomerDTO> records = repository.findAll();
        List<CustomerVM> result = new ArrayList<>();

        for (CustomerDTO record :
                records) {
            CustomerVM vm = new CustomerVM();
            vm.id = record.getId();
            vm.name = record.getFirstName();
            vm.email = record.getEmail();
            vm.phoneNumber = record.getPhoneNumber();

            result.add(vm);
        }

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerVM> getById(Integer id) {

        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            CustomerDTO record = repository.getOne(id);

            CustomerVM vm = new CustomerVM();
            vm.id = record.getId();
            vm.name = record.getFirstName();
            vm.email = record.getEmail();
            vm.phoneNumber = record.getPhoneNumber();

            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<CustomerDTO> add(CustomerAddVM model) {
        CustomerDTO entity = new CustomerDTO();
        entity.setFirstName(model.firstName);
        entity.setLastName(model.lastName);
        entity.setEmail(model.email);
        entity.setPhoneNumber(model.phoneNumber);

        repository.save(entity);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDTO> update(Integer id, CustomerUpdateVM model) {
        CustomerDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setFirstName(model.firstName);
            entity.setLastName(model.lastName);
            entity.setPhoneNumber(model.phoneNumber);
            entity.setEmail(model.email);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CustomerVM> delete(Integer id) {
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
    public ResponseEntity<List<CustomerVM>> getIsDeleted(boolean showIsDeleted) {
        return null;
    }
}
