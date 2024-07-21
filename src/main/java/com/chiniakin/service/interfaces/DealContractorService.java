package com.chiniakin.service.interfaces;

import com.chiniakin.model.dealcontractor.SaveDealContractorModel;

import java.util.UUID;

public interface DealContractorService {

    void saveDealContractor(SaveDealContractorModel saveDealContractorModel);

    void deleteDealContractorById(UUID dealContractorId);

}
