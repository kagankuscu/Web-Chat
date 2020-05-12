package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.IdValidator;
import com.Feelfree2code.STA.model.domain.ProjectContactDTO;
import com.Feelfree2code.STA.model.viewModel.ProjectContactAddVM;
import com.Feelfree2code.STA.model.viewModel.ProjectContactUpdate;
import com.Feelfree2code.STA.model.viewModel.ProjectContactVM;
import com.Feelfree2code.STA.subStructure.IProjectContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectContactService implements IBaseService<ProjectContactVM, ProjectContactAddVM,
        ProjectContactUpdate, ProjectContactDTO>, IdValidator {

    private final IProjectContactRepository repository;

    public ProjectContactService(IProjectContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<ProjectContactVM>> get() {
        List<ProjectContactDTO> records = repository.findAll();

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ProjectContactVM> results = new ArrayList<>();

        for (ProjectContactDTO record : records) {
            ProjectContactVM vm = new ProjectContactVM();
            vm.id = record.getId();
//            vm.customerId = record.getCustomerId().getId();
//            vm.projectId = record.getProjectId().getId();
            vm.priortyIndex = record.getPriortyIndex();

            results.add(vm);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectContactVM> getById(Integer id) {

        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            ProjectContactDTO record = repository.getOne(id);

            ProjectContactVM vm = new ProjectContactVM();
            vm.id = record.getId();
//            vm.priortyIndex = record.getPriortyIndex();
//            vm.projectId = record.getProjectId().getId();
//            vm.customerId = record.getCustomerId().getId();

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    @Override
    public ResponseEntity<ProjectContactDTO> add(ProjectContactAddVM model) {
//        CustomerDTO customerEntity = customerRepository.getOne(model.customerId);
//        ProjectDTO projectEntity = projectRepository.getOne(model.projectId);

        ProjectContactDTO entity = new ProjectContactDTO();

//        entity.setCustomerId(customerEntity);
//        entity.setProjectId(projectEntity);
        entity.setPriortyIndex(model.priortyIndex);

        return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProjectContactDTO> update(Integer id, ProjectContactUpdate model) {
        ProjectContactDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setPriortyIndex(model.priortyIndex);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ProjectContactVM> delete(Integer id) {

        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<List<ProjectContactVM>> getIsDeleted(boolean showIsDeleted) {
        return null;
    }
}
