package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.ProjectDTO;
import com.Feelfree2code.STA.model.viewModel.ProjectAddVM;
import com.Feelfree2code.STA.model.viewModel.ProjectUpdateVM;
import com.Feelfree2code.STA.model.viewModel.ProjectVM;
import com.Feelfree2code.STA.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/project")
public class ProjectController {

    private ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> add(@RequestBody @Valid ProjectAddVM project) {
        return service.add(project);
    }

    @GetMapping
    public ResponseEntity<List<ProjectVM>> get() {
        return service.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProjectVM> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable Integer id,
                                             @RequestBody @Valid ProjectUpdateVM project) {
        return service.update(id, project);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProjectVM> delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
