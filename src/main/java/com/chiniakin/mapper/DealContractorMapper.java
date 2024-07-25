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

/**
 * Класс для преобразования контрагентов сделок и их ролей.
 *
 * @author ChiniakinD
 */
@Component
@RequiredArgsConstructor
public class DealContractorMapper {

    private final ModelMapper mapper;

    /**
     * Объединяет dealContractor и roles в объект DealContractorResponse.
     *
     * @param dealContractor сущность контрагента сделки.
     * @param roles          список ролей контрагента сделки.
     * @return объект DealContractorResponse.
     */
    public DealContractorResponse mapDealContractor(DealContractor dealContractor, List<ContractorRole> roles) {
        return mapper.map(dealContractor, DealContractorResponse.class)
                .setDealContractorRoleModel(mapRolesToDto(roles));
    }

    /**
     * Преобразует список ContractorRole в список ContractorRoleModel.
     *
     * @param roles список ролей контрагента сделки.
     * @return список моделей ролей контрагента сделки.
     */
    private List<ContractorRoleModel> mapRolesToDto(List<ContractorRole> roles) {
        return roles != null
                ? roles.stream()
                    .map(role -> mapper.map(role, ContractorRoleModel.class))
                    .toList()
                : new ArrayList<>();
    }

    /**
     * Передает данные из saveDealContractorModel в dealContractor.
     *
     * @param dealContractor          сущность контрагента сделки.
     * @param saveDealContractorModel модель контрагента сделки для сохранения/обновления.
     */
    public void merge(DealContractor dealContractor, SaveDealContractorModel saveDealContractorModel) {
        dealContractor.setActive(true)
                .setContractorId(saveDealContractorModel.getContractorID())
                .setId(saveDealContractorModel.getId())
                .setInn(saveDealContractorModel.getInn())
                .setName(saveDealContractorModel.getName())
                .setMain(saveDealContractorModel.isMain());
    }

}
