package com.chiniakin.service;

import com.chiniakin.entity.Deal;
import com.chiniakin.entity.DealContractor;
import com.chiniakin.entity.DealStatus;
import com.chiniakin.enums.DealStatusEnum;
import com.chiniakin.mapper.DealContractorMapper;
import com.chiniakin.mapper.DealMapper;

import com.chiniakin.model.deal.ChangeStatusModel;
import com.chiniakin.model.deal.DealFilter;
import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.SaveDealModel;
import com.chiniakin.model.deal.DealContractorResponse;
import com.chiniakin.repository.ContractorRoleRepository;
import com.chiniakin.repository.DealContractorRepository;
import com.chiniakin.repository.DealRepository;
import com.chiniakin.repository.DealStatusRepository;
import com.chiniakin.service.interfaces.DealService;
import com.chiniakin.service.interfaces.HttpClientService;
import com.chiniakin.specification.DealServiceSpecification;
import com.chiniakin.util.auth.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для работы со сделками.
 *
 * @author ChiniakinD
 */
@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealMapper dealMapper;
    private final DealRepository dealRepository;
    private final DealStatusRepository dealStatusRepository;
    private final DealContractorRepository dealContractorRepository;
    private final DealContractorMapper dealContractorMapper;
    private final ContractorRoleRepository contractorRoleRepository;
    private final HttpClientService httpClientService;

    public DealModel getDealById(UUID id) {
        Deal deal = dealRepository.findByIdWithDetailsOrThrow(id);
        DealModel dealModel = dealMapper.toModel(deal);
        List<DealContractorResponse> collect = dealContractorRepository.findAllByDealId(id).stream()
                .map(x -> dealContractorMapper.mapDealContractor(x, contractorRoleRepository.findAllRolesByDealContractorId(x.getId())))
                .collect(Collectors.toList());
        dealModel.setContractors(collect);

        return dealModel;
    }

    public void changeStatus(ChangeStatusModel changeStatusModel) {
        Deal deal = dealRepository.findByIdWithDetailsOrThrow(changeStatusModel.getDealId());
        DealContractor mainContractor = dealContractorRepository.findMainContractor(changeStatusModel.getDealId());
        sendMessageToContractor(changeStatusModel, mainContractor, deal);

        DealStatus dealStatus = dealStatusRepository.findDealStatusByIdOrThrow(changeStatusModel.getDealStatusId());

        dealRepository.changeStatusDeal(changeStatusModel.getDealId(), dealStatus);
    }

    public void saveDeal(SaveDealModel saveDealModel) {
        Deal deal = dealRepository.findById(saveDealModel.getId()).orElse(new Deal());
        dealMapper.merge(deal, saveDealModel);
        deal.setStatus(dealStatusRepository.findDealStatusByIdOrThrow("DRAFT"));
        dealRepository.save(deal);
    }

    public Page<DealModel> getDealsByFilters(DealFilter dealFilter) {
        Pageable pageable = PageRequest.of(dealFilter.getPage() - 1, dealFilter.getSize());
        Page<Deal> deals = dealRepository.findAll(DealServiceSpecification.buildSpecification(dealFilter, SecurityUtil.getUserRoles()), pageable);
        List<DealModel> collect = deals.getContent().stream().map(dealMapper::toModel).collect(Collectors.toList());
        return new PageImpl<>(collect, deals.getPageable(), deals.getTotalElements());
    }

    private void sendMessageToContractor(ChangeStatusModel changeStatusModel, DealContractor mainContractor, Deal deal) {
        if (dealContractorRepository.checkMain(mainContractor.getContractorId()) <= 1) {
            if ((deal.getStatus().getId().equals(DealStatusEnum.getDealStatusEnumById("DRAFT")) &&
                    changeStatusModel.getDealStatusId().equals(DealStatusEnum.getDealStatusEnumById("DRAFT")))) {
                httpClientService.sendRequestToContractor(mainContractor.getContractorId(), Boolean.TRUE);
            } else if (deal.getStatus().getId().equals(DealStatusEnum.getDealStatusEnumById("ACTIVE")) &&
                    changeStatusModel.getDealStatusId().equals(DealStatusEnum.getDealStatusEnumById("CLOSED"))) {
                httpClientService.sendRequestToContractor(mainContractor.getContractorId(), Boolean.FALSE);
            } else if (deal.getStatus().getId().equals(DealStatusEnum.getDealStatusEnumById("CLOSED")) &&
                    changeStatusModel.getDealStatusId().equals(DealStatusEnum.getDealStatusEnumById("ACTIVE"))) {
                httpClientService.sendRequestToContractor(mainContractor.getContractorId(), Boolean.TRUE);
            }
        }
    }

}
