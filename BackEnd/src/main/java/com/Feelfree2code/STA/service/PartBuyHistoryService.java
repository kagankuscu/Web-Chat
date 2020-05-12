package com.Feelfree2code.STA.service;

import com.Feelfree2code.STA.common.IdValidator;
import com.Feelfree2code.STA.model.domain.PartBuyHistoryDTO;
import com.Feelfree2code.STA.model.viewModel.PartBuyHistoryAddVM;
import com.Feelfree2code.STA.model.viewModel.PartBuyHistoryUpdateVM;
import com.Feelfree2code.STA.model.viewModel.PartBuyHistoryVM;
import com.Feelfree2code.STA.subStructure.IPartBuyHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartBuyHistoryService implements IBaseService<PartBuyHistoryVM, PartBuyHistoryAddVM, PartBuyHistoryUpdateVM, PartBuyHistoryDTO>, IdValidator {

    private IPartBuyHistoryRepository repository;

    public PartBuyHistoryService(IPartBuyHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<PartBuyHistoryVM>> get() {

        List<PartBuyHistoryDTO> records = repository.findAll();

        if (records.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<PartBuyHistoryVM> result = new ArrayList<>();

        for (PartBuyHistoryDTO record :
                records) {
            PartBuyHistoryVM vm = new PartBuyHistoryVM();
            vm.id = record.getId();
            vm.amount = record.getAmount();
            vm.dateTime = record.getDateTime();
            vm.partId = record.getPartId().getId();
            vm.price = record.getPrice();

            result.add(vm);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PartBuyHistoryVM> getById(Integer id) {

        try {
            if (isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            PartBuyHistoryDTO record = repository.getOne(id);

            PartBuyHistoryVM vm = new PartBuyHistoryVM();
            vm.id = record.getId();
            vm.amount = record.getAmount();
            vm.dateTime = record.getDateTime();
            vm.partId = record.getPartId().getId();
            vm.price = record.getPrice();
            return new ResponseEntity<>(vm, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<PartBuyHistoryDTO> add(PartBuyHistoryAddVM model) {
        PartBuyHistoryDTO entity = new PartBuyHistoryDTO();

        entity.setAmount(model.amount);
        entity.setDateTime(model.dateTime);
//        entity.setPartId(model.partId);
        entity.setPrice(model.price);

        repository.save(entity);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PartBuyHistoryDTO> update(Integer id, PartBuyHistoryUpdateVM model) {
        PartBuyHistoryDTO entity = null;

        if (repository.existsById(id)) {
            entity = repository.getOne(id);

            entity.setAmount(model.amount);
            entity.setDateTime(model.dateTime);
//        entity.setPartId(model.partId);
            entity.setPrice(model.price);

            return new ResponseEntity<>(repository.save(entity), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PartBuyHistoryVM> delete(Integer id) {
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
    public ResponseEntity<List<PartBuyHistoryVM>> getIsDeleted(boolean showIsDeleted) {
        return null;
    }
}
