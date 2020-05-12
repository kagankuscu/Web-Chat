package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.IdValidator;
import com.Feelfree2code.STA.model.domain.ProjectDTO;
import com.Feelfree2code.STA.model.viewModel.ProjectAddVM;
import com.Feelfree2code.STA.model.viewModel.ProjectUpdateVM;
import com.Feelfree2code.STA.model.viewModel.ProjectVM;
import com.Feelfree2code.STA.subStructure.IProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService implements IBaseService<ProjectVM, ProjectAddVM,
        ProjectUpdateVM, ProjectDTO>, IdValidator {

    private final IProjectRepository repository;

    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<ProjectVM>> get() {
        List<ProjectDTO> records = repository.findAll();
        List<ProjectVM> results = new ArrayList<>();

        for (ProjectDTO record : records) {
            ProjectVM vm = new ProjectVM();
            vm.id = record.getId();
            vm.title = record.getTitle();
            vm.address = record.getAddress();
            vm.startDate = record.getStartTime();
            vm.endDate = record.getEndTime();

            results.add(vm);
        }

        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectVM> getById(Integer id) {

        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            ProjectDTO record = repository.getOne(id);

            ProjectVM vm = new ProjectVM();
            vm.id = record.getId();
            vm.title = record.getTitle();
            vm.address = record.getAddress();
            vm.startDate = record.getStartTime();
            vm.endDate = record.getEndTime();

            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<ProjectDTO> add(ProjectAddVM model) {
        ProjectDTO entity = new ProjectDTO();
        entity.setTitle(model.title);
        entity.setAddress(model.address);
        entity.setStartTime(model.startDate);
        entity.setEndTime(model.endDate);

        repository.save(entity);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectDTO> update(Integer id, ProjectUpdateVM model) {
        ProjectDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setTitle(model.title);
            entity.setAddress(model.address);
            entity.setStartTime(model.startDate);
            entity.setEndTime(model.endDate);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProjectVM> delete(Integer id) {
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
    public ResponseEntity<List<ProjectVM>> getIsDeleted(boolean showIsDeleted) {
        return null;
    }
}
