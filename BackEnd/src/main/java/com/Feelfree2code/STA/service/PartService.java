package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.IdValidator;
import com.Feelfree2code.STA.model.domain.PartDTO;
import com.Feelfree2code.STA.model.viewModel.PartAddVM;
import com.Feelfree2code.STA.model.viewModel.PartUpdateVM;
import com.Feelfree2code.STA.model.viewModel.PartVM;
import com.Feelfree2code.STA.subStructure.IPartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService implements IBaseService<PartVM, PartAddVM, PartUpdateVM, PartDTO>, IdValidator {

    private IPartRepository repository;

    public PartService(IPartRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<PartVM>> get() {

        List<PartDTO> records = repository.findAll();

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<PartVM> result = new ArrayList<>();
        for (PartDTO record : records) {
            PartVM vm = new PartVM();
            vm.id = record.getId();
            vm.name = record.getName();
            vm.amount = record.getAmount();
            vm.partType = record.getPartType();
            vm.specs = record.getSpecs();

            result.add(vm);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PartVM> getById(Integer id) {
        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            PartDTO record = repository.getOne(id);

            PartVM vm = new PartVM();
            vm.id = record.getId();
            vm.amount = record.getAmount();
            vm.partType = record.getPartType();
            vm.specs = record.getSpecs();

            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<PartDTO> add(PartAddVM model) {
        PartDTO entity = new PartDTO();
        entity.setName(model.name);
        entity.setAmount(model.amount);
        entity.setPartType(model.partType);
        entity.setSpecs(model.specs);

        repository.save(entity);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PartDTO> update(Integer id, PartUpdateVM model) {
        PartDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setName(model.name);
            entity.setAmount(model.amount);
            entity.setPartType(model.partType);
            entity.setSpecs(model.specs);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PartVM> delete(Integer id) {
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
    public ResponseEntity<List<PartVM>> getIsDeleted(boolean showIsDeleted) {
        return null;
    }
}
