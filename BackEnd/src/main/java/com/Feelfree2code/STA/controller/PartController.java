package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.PartDTO;
import com.Feelfree2code.STA.model.viewModel.PartAddVM;
import com.Feelfree2code.STA.model.viewModel.PartUpdateVM;
import com.Feelfree2code.STA.model.viewModel.PartVM;
import com.Feelfree2code.STA.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/part")
public class PartController {

    private PartService service;

    public PartController(PartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PartDTO> add(@RequestBody @Valid PartAddVM partAddVM) {
        return service.add(partAddVM);
    }

    @GetMapping
    public ResponseEntity<List<PartVM>> get() {
        return service.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PartVM> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PartDTO> update(@PathVariable Integer id, @RequestBody @Valid PartUpdateVM partUpdateVM) {
        return service.update(id,partUpdateVM);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PartVM> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
