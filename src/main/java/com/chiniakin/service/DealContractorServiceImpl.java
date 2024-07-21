package com.chiniakin.service;

import com.chiniakin.entity.DealContractor;
import com.chiniakin.mapper.DealContractorMapper;
import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import com.chiniakin.repository.DealContractorRepository;
import com.chiniakin.repository.DealRepository;
import com.chiniakin.service.interfaces.DealContractorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Реализация сервиса для работы с контрагентами сделки.
 *
 * @author ChiniakinD
 */
@Service
@RequiredArgsConstructor
public class DealContractorServiceImpl implements DealContractorService {

    private final DealContractorRepository dealContractorRepository;
    private final DealContractorMapper dealContractorMapper;
    private final DealRepository dealRepository;

    public void saveDealContractor(SaveDealContractorModel saveDealContractorModel) {
        DealContractor dealContractor = dealContractorRepository.findById(saveDealContractorModel.getId()).orElse(new DealContractor());
        dealContractorMapper.merge(dealContractor, saveDealContractorModel);
        dealContractor.setDeal(dealRepository.findById(saveDealContractorModel.getDealId()).orElseThrow(() -> new RuntimeException("Сделка в базе данных не найдена")));
        dealContractorRepository.save(dealContractor);
    }

    public void deleteDealContractorById(UUID dealContractorId) {
        dealContractorRepository.logicDelete(dealContractorId);
    }

}
