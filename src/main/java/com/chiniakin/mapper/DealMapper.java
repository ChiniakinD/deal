package com.chiniakin.mapper;

import com.chiniakin.entity.Deal;
import com.chiniakin.entity.DealStatus;

import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.DealStatusResponse;
import com.chiniakin.model.deal.DealTypeResponse;
import com.chiniakin.model.deal.SaveDealModel;
import com.chiniakin.model.deal.DealContractorResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DealMapper {

    private final ModelMapper mapper;
    private final DealContractorMapper dealContractorMapper;
    @Autowired
    @Qualifier("mergeMapper")
    private final ModelMapper mergeMapper;

    public DealModel toModel(Deal deal) {
        return mapper.map(deal, DealModel.class)
                .setDealTypeResponse(mapper.map(deal.getType(), DealTypeResponse.class))
                .setDealStatusResponse(mapper.map(deal.getStatus(), DealStatusResponse.class))
                .setAvailabilityDate(LocalDate.from(deal.getAvailabilityDate()))
                .setContractors(deal.getDealContractor().stream().map(x -> mapper.map(x, DealContractorResponse.class)).toList());
    }

    public void merge(Deal deal, SaveDealModel saveDealModel) {
        deal.setId(saveDealModel.getId())
                .setDescription(saveDealModel.getDescription())
                .setAgreementNumber(saveDealModel.getAgreementNumber())
                .setAgreementDate(saveDealModel.getAgreementDate())
                .setAgreementStartDt(saveDealModel.getAgreementStartDt())
                .setAvailabilityDate(saveDealModel.getAvailabilityDate())
                .setSum(saveDealModel.getSum())
                .setCloseDt(saveDealModel.getCloseDt());
    }

    private DealStatus createDefaultDealStatusResponse() {
        DealStatus dealStatus = new DealStatus();
        dealStatus.setId("DRAFT");
        dealStatus.setName("Черновик");
        return dealStatus;
    }

}
