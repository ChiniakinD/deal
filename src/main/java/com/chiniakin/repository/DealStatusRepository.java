package com.chiniakin.repository;

import com.chiniakin.entity.DealStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Репозиторий для работы со статусами сделок.
 *
 * @author ChiniakinD
 */
public interface DealStatusRepository extends JpaRepository<DealStatus, String> {

    /**
     * Получает статус сделки по id.
     *
     * @param id идентификатор статуса.
     * @return статус сделки
     */
    Optional<DealStatus> findDealStatusById(String id);

    /**
     * Получает статус сделки по id или выбрасывает исключение.
     *
     * @param id идентификатор статуса.
     * @return статус сделки.
     */
    default DealStatus findDealStatusByIdOrThrow(String id) {
        return findDealStatusById(id).orElseThrow(RuntimeException::new);
    }

}
