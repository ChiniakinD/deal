package com.chiniakin.repository;

import com.chiniakin.entity.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealStatusRepository extends JpaRepository<DealStatus, String> {

    Optional<DealStatus> findDealStatusById(String id);

    default DealStatus findDealStatusByIdOrThrow(String id) {
        return findDealStatusById(id).orElseThrow(RuntimeException::new);
    }

}
