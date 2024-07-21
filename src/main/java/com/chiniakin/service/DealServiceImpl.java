package com.chiniakin.service;

import com.chiniakin.entity.Deal;
import com.chiniakin.entity.DealStatus;
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
import com.chiniakin.specification.DealServiceSpecification;
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

    public DealModel getDealById(UUID id) {
        Deal deal = dealRepository.findByIdWithDetailsOrThrow(id);
        DealModel dealModel = dealMapper.toModel(deal);
        List<DealContractorResponse> collect = dealContractorRepository.findAllByDealId(id).stream()
                .map(x -> dealContractorMapper.mapDealContractor(x, contractorRoleRepository.findAllRolesByDealId(x.getId())))
                .collect(Collectors.toList());
        dealModel.setContractors(collect);
        return dealModel;
    }

    public void changeStatus(ChangeStatusModel changeStatusModel) {
        Deal deal = dealRepository.findByIdWithDetailsOrThrow(changeStatusModel.getDealId());
        DealStatus dealStatus = dealStatusRepository.findDealStatusByIdOrThrow(changeStatusModel.getDealStatusId());
        deal.setStatus(dealStatus);
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
        Page<Deal> deals = dealRepository.findAll(DealServiceSpecification.buildSpecification(dealFilter), pageable);
        List<DealModel> collect = deals.getContent().stream().map(dealMapper::toModel).collect(Collectors.toList());
        return new PageImpl<>(collect, deals.getPageable(), deals.getTotalElements());
    }

}
