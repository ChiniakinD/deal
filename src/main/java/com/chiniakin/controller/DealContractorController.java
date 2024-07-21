package com.chiniakin.controller;

import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import com.chiniakin.service.interfaces.DealContractorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/deal-contractor")
public class DealContractorController {

    private DealContractorService dealContractorService;

    @PutMapping("/save")
    public void saveDealContractor(@RequestBody SaveDealContractorModel saveDealContractorModel) {
        dealContractorService.saveDealContractor(saveDealContractorModel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDealContractorById(@PathVariable UUID id) {
        dealContractorService.deleteDealContractorById(id);
    }

}
