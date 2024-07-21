package com.chiniakin.repository;

import com.chiniakin.entity.DealType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с типом сделок.
 *
 * @author ChiniakinD
 */
public interface DealTypeRepository extends JpaRepository<DealType, String> {
}
