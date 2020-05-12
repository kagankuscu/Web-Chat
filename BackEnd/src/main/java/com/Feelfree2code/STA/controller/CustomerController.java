package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.CustomerDTO;
import com.Feelfree2code.STA.model.viewModel.CustomerAddVM;
import com.Feelfree2code.STA.model.viewModel.CustomerUpdateVM;
import com.Feelfree2code.STA.model.viewModel.CustomerVM;
import com.Feelfree2code.STA.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<CustomerDTO> add(@RequestBody @Valid CustomerAddVM customer) {
        return service.add(customer);
    } 
    
    @GetMapping
    public ResponseEntity<List<CustomerVM>> get() {
        return service.get();
    } 
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerVM> getById(@PathVariable Integer id) {
        return service.getById(id);
    } 
    
    @PutMapping(path = "{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @RequestBody @Valid CustomerUpdateVM customer) {
        return service.update(id, customer);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<CustomerVM> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
