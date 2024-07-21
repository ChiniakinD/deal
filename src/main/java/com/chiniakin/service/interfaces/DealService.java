package com.chiniakin.service.interfaces;

import com.chiniakin.model.deal.ChangeStatusModel;
import com.chiniakin.model.deal.DealFilter;
import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.SaveDealModel;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DealService {

    DealModel getDealById(UUID id);

    void changeStatus(ChangeStatusModel changeStatusModel);

    void saveDeal(SaveDealModel saveDealModel);

    Page<DealModel> getDealsByFilters(DealFilter dealFilter);

}
