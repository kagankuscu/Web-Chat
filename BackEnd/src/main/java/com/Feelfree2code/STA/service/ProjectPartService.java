package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.IdValidator;
import com.Feelfree2code.STA.model.domain.ProjectPartDTO;
import com.Feelfree2code.STA.model.viewModel.ProjectPartAddVM;
import com.Feelfree2code.STA.model.viewModel.ProjectPartUpdateVM;
import com.Feelfree2code.STA.model.viewModel.ProjectPartVM;
import com.Feelfree2code.STA.subStructure.IProjectPartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectPartService implements IBaseService<ProjectPartVM, ProjectPartAddVM,
        ProjectPartUpdateVM, ProjectPartDTO>, IdValidator {

    private final IProjectPartRepository repository;

    public ProjectPartService(IProjectPartRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<ProjectPartVM>> get() {
        List<ProjectPartDTO> records = repository.findAll();
        List<ProjectPartVM> result = new ArrayList<>();

        for (ProjectPartDTO record :
                records) {
            ProjectPartVM vm = new ProjectPartVM();
            vm.id = record.getId();
            vm.amount = record.getAmount();
//            vm.partId = record.getPartId().getId();
//            vm.projectId = record.getProjectId().getId();

            result.add(vm);
        }

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ProjectPartVM> getById(Integer id) {
        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            ProjectPartDTO record = repository.getOne(id);

            ProjectPartVM vm = new ProjectPartVM();
            vm.id = record.getId();
            vm.amount = record.getAmount();
//            vm.partId = record.getPartId().getId();
//            vm.projectId = record.getProjectId().getId();

            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<ProjectPartDTO> add(ProjectPartAddVM model) {
        ProjectPartDTO entity = new ProjectPartDTO();

        entity.setAmount(model.amount);
//            entity.setPartId(model.partId);
//            entity.setProjectId(model.projectId);

        return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectPartDTO> update(Integer id, ProjectPartUpdateVM model) {
        ProjectPartDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setAmount(model.amount);
//            entity.setPartId(model.partId);
//            entity.setProjectId(model.projectId);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProjectPartVM> delete(Integer id) {
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
    public ResponseEntity<List<ProjectPartVM>> getIsDeleted(boolean showIsDeleted) {
        return null;
    }
}
