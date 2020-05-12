package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.ProjectPartDTO;
import com.Feelfree2code.STA.model.viewModel.ProjectPartAddVM;
import com.Feelfree2code.STA.model.viewModel.ProjectPartUpdateVM;
import com.Feelfree2code.STA.model.viewModel.ProjectPartVM;
import com.Feelfree2code.STA.service.ProjectPartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/projectPart")
public class ProjectPartController  {

    private final ProjectPartService service;

    public ProjectPartController(ProjectPartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectPartDTO> add(@RequestBody @Valid ProjectPartAddVM project) {
        return service.add(project);
    }

    @GetMapping
    public ResponseEntity<List<ProjectPartVM>> get() {
        return service.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectPartVM> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectPartDTO> update(@PathVariable Integer id,
                                                 @RequestBody @Valid ProjectPartUpdateVM project) {
        return service.update(id, project);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjectPartVM> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
