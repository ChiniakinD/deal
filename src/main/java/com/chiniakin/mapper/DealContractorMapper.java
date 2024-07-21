package com.chiniakin.mapper;

import com.chiniakin.entity.DealContractor;
import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DealContractorMapper {

    public void merge(DealContractor dealContractor, SaveDealContractorModel saveDealContractorModel) {
        dealContractor.setActive(true)
                .setContractorId(saveDealContractorModel.getContractorID())
                .setId(saveDealContractorModel.getId())
                .setInn(saveDealContractorModel.getInn())
                .setName(saveDealContractorModel.getName());
    }

}
