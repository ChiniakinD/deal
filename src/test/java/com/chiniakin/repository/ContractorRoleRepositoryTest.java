package com.chiniakin.repository;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.ContractorRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Testcontainers
@SpringBootTest(classes = TestBeans.class)
class ContractorRoleRepositoryTest {

    @Autowired
    private ContractorRoleRepository contractorRoleRepository;

    @BeforeEach
    void setUp() {

    }
    @Test
    @Sql("/insertForTests/insert.sql")
    void shouldReturnAllRolesForDealContractorId() {
        List<ContractorRole> all = contractorRoleRepository.findAllRolesByDealContractorId(UUID.fromString("8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec"));
        assertEquals(1, all.size());
        assertEquals("BORROWER", all.get(0).getId());
        assertEquals("Заемщик", all.get(0).getName());
        assertEquals("BORROWER", all.get(0).getCategory());
        assertTrue(all.get(0).isActive());

    }
}
