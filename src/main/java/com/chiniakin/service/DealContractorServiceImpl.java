package com.chiniakin.service;

import com.chiniakin.entity.DealContractor;
import com.chiniakin.exception.DealNotFoundException;
import com.chiniakin.exception.SecondMainDealContractorException;
import com.chiniakin.mapper.DealContractorMapper;
import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import com.chiniakin.repository.DealContractorRepository;
import com.chiniakin.repository.DealRepository;
import com.chiniakin.service.interfaces.DealContractorService;
import com.chiniakin.service.interfaces.HttpClientService;
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
    private final HttpClientService httpClientService;

    public void saveDealContractor(SaveDealContractorModel saveDealContractorModel) {
        DealContractor dealContractor = dealContractorRepository.findById(saveDealContractorModel.getId()).orElse(new DealContractor());
        if (saveDealContractorModel.isMain() && dealContractorRepository.existsByDealIdAndMainTrue(saveDealContractorModel.getDealId())) {
            throw new SecondMainDealContractorException("Невозможно добавить более 1 главного контрагента на 1 сделку.");
        }
        dealContractorMapper.merge(dealContractor, saveDealContractorModel);
        dealContractor.setDeal(dealRepository.findById(saveDealContractorModel.getDealId()).orElseThrow(()
                -> new DealNotFoundException("Сделка с id " + saveDealContractorModel.getDealId() + "не найдена.")));
        dealContractorRepository.save(dealContractor);
    }

    public void deleteDealContractorById(UUID dealContractorId) {
        DealContractor mainContractor = dealContractorRepository.findMainContractor(dealContractorId);
        if (dealContractorRepository.checkMain(mainContractor.getContractorId()) == 1) {
            httpClientService.sendRequestToContractor(mainContractor.getContractorId(), Boolean.FALSE);
        }
        dealContractorRepository.logicDelete(dealContractorId);
    }

}
