package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.ProjectContactDTO;
import com.Feelfree2code.STA.model.viewModel.ProjectContactAddVM;
import com.Feelfree2code.STA.model.viewModel.ProjectContactUpdate;
import com.Feelfree2code.STA.model.viewModel.ProjectContactVM;
import com.Feelfree2code.STA.service.ProjectContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/projectContact")
public class ProjectContactController {

    private ProjectContactService service;

    public ProjectContactController(ProjectContactService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectContactDTO> add(@RequestBody @Valid ProjectContactAddVM projectContact) {
        return service.add(projectContact);
    }

    @GetMapping
    public ResponseEntity<List<ProjectContactVM>> get() {
        return service.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectContactVM> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectContactDTO> update(@PathVariable Integer id,
                                                     @RequestBody @Valid ProjectContactUpdate projectContactUpdate) {
        return service.update(id, projectContactUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjectContactVM> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
