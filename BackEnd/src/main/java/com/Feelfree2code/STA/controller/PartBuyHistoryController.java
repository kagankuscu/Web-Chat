package com.Feelfree2code.STA.controller;

import com.Feelfree2code.STA.model.domain.PartBuyHistoryDTO;
import com.Feelfree2code.STA.model.viewModel.PartBuyHistoryAddVM;
import com.Feelfree2code.STA.model.viewModel.PartBuyHistoryUpdateVM;
import com.Feelfree2code.STA.model.viewModel.PartBuyHistoryVM;
import com.Feelfree2code.STA.service.PartBuyHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/partBuyHistory")
public class PartBuyHistoryController {

    private PartBuyHistoryService service;

    public PartBuyHistoryController(PartBuyHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PartBuyHistoryDTO> add(@RequestBody @Valid PartBuyHistoryAddVM partBuyHistory) {
        return service.add(partBuyHistory);
    }

    @GetMapping
    public ResponseEntity<List<PartBuyHistoryVM>> get() {
        return service.get();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PartBuyHistoryVM> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PartBuyHistoryDTO> update(@PathVariable Integer id, @RequestBody @Valid PartBuyHistoryUpdateVM partBuyHistoryUpdateVM) {
        return service.update(id,partBuyHistoryUpdateVM);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PartBuyHistoryVM> delete(@PathVariable Integer id) {
        return service.delete(id);
    } 
}
