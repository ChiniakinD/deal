package com.chiniakin.mapper;

import com.chiniakin.entity.Deal;

import com.chiniakin.model.deal.DealContractorResponse;
import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.DealStatusResponse;
import com.chiniakin.model.deal.DealTypeResponse;
import com.chiniakin.model.deal.SaveDealModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Класс для преобразования классов сделок.
 *
 * @author ChiniakinD
 */
@Component
@RequiredArgsConstructor
public class DealMapper {

    private final ModelMapper mapper;

    /**
     * Конвертирует Deal в модель DealModel.
     *
     * @param deal сущность сделки.
     * @return dealModel модель сделки.
     */
    public DealModel toModel(Deal deal) {
        return mapper.map(deal, DealModel.class)
                .setDealTypeResponse(mapper.map(deal.getType(), DealTypeResponse.class))
                .setDealStatusResponse(mapper.map(deal.getStatus(), DealStatusResponse.class))
                .setAvailabilityDate(LocalDate.from(deal.getAvailabilityDate()))
                .setContractors(deal.getDealContractor().stream().map(x -> mapper.map(x, DealContractorResponse.class)).toList());
    }

    /**
     * Передает данные из saveDealModel в deal.
     *
     * @param deal          сущность сделки.
     * @param saveDealModel модель сделки для сохранения/обновления.
     */
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

}
