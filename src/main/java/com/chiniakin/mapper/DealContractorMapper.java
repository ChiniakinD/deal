package com.chiniakin.mapper;

import com.chiniakin.entity.ContractorRole;
import com.chiniakin.entity.DealContractor;
import com.chiniakin.model.contractorrole.ContractorRoleModel;
import com.chiniakin.model.deal.DealContractorResponse;
import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DealContractorMapper {

    private final ModelMapper mapper;

    public DealContractorResponse mapDealContractor(DealContractor dealContractor, List<ContractorRole> roles) {
        return mapper.map(dealContractor, DealContractorResponse.class)
                .setDealContractorRoleModel(mapRolesToDto(roles));
    }

    private List<ContractorRoleModel> mapRolesToDto(List<ContractorRole> roles) {
        return roles != null
                ? roles.stream()
                .map(role -> mapper.map(role, ContractorRoleModel.class))
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    public void merge(DealContractor dealContractor, SaveDealContractorModel saveDealContractorModel) {
        dealContractor.setActive(true)
                .setContractorId(saveDealContractorModel.getContractorID())
                .setId(saveDealContractorModel.getId())
                .setInn(saveDealContractorModel.getInn())
                .setName(saveDealContractorModel.getName());
    }

}
