package com.chiniakin.repository;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.DealContractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;


@Testcontainers
@SpringBootTest(classes = TestBeans.class)
public class DealContractorRepositoryTest {

    @Autowired
    private DealContractorRepository dealContractorRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void shouldReturnTrueIfMainContractorExistsForDealId() {
        assertTrue(dealContractorRepository.existsByDealIdAndMainTrue(UUID.fromString("d3b07384-d9a4-4a4a-b1de-5c2e5dcf930d")));
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void shouldReturnAllActiveContractorsForDealId() {
        List<DealContractor> dealContractors = dealContractorRepository.findAllByDealId(UUID.fromString("d3b07384-d9a4-4a4a-b1de-5c2e5dcf930d"));
        assertFalse(dealContractors.isEmpty());
        for (DealContractor dealContractor : dealContractors) {
            assertTrue(dealContractor.isActive());
        }
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void shouldReturnCorrectMainContractorForDealId() {
        DealContractor mainDealContractor = dealContractorRepository.findMainContractor(UUID.fromString("d3b07384-d9a4-4a4a-b1de-5c2e5dcf930d"));
        assertNotNull(mainDealContractor);
        assertTrue(mainDealContractor.isMain());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void deleteDealContractorShouldBeSuccessfulDeleted() {
        UUID dealContractorId = UUID.fromString("8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec");
        dealContractorRepository.logicDelete(dealContractorId);
        assertFalse(dealContractorRepository.findById(dealContractorId).orElseThrow().isActive());
    }

}
