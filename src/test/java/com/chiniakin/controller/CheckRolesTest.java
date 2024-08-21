package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import com.chiniakin.service.interfaces.DealContractorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class CheckRolesTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DealContractorService dealContractorService;

    @BeforeEach
    void setUp() {
        String username = "user";
        List<GrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority("USER"),
                new SimpleGrantedAuthority("ADMIN")
        );
        UserDetails userDetails = new User(username, "password", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void saveContractorWithoutAppropriateRoleShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deal-contractor/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "id": "561b5244-29ed-4d18-9145-193775a1d0dc",
                                   "deal_id": "c3d5b726-92e8-4bb4-9826-66f72fce1c4d",
                                   "contractor_id": "1233",
                                   "name": "123",
                                   "main": "true"
                                 }
                                """
                        ))
                .andExpect(status().isForbidden());
    }

    @Test
    void addContractorToRoleWithoutAppropriateRoleShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/contractor-to-role/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                          "id": "8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec",
                                          "role_id": {
                                              "id": "BORROWER",
                                              "name": "Заемщик",
                                              "category": "BORROWER",
                                              "isActive": true
                                          }
                                      }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void getDealByIdWithUserRoleShouldReturnCorrectDealModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deal/{id}", "c3d5b726-92e8-4bb4-9826-66f72fce1c4d"))
                .andExpect(content().json("""
                        {
                             "id": "c3d5b726-92e8-4bb4-9826-66f72fce1c4d",
                             "description": "Сделка по оказанию услуг",
                             "sum": 75000.00,
                             "agreement_number": "AG987654",
                             "agreement_date": "2024-03-10",
                             "agreement_start_dt": "2024-03-15T14:00:00",
                             "availability_date": "2025-03-10",
                             "type": {
                                 "id": "OTHER",
                                 "name": "Иное"
                             },
                             "status": {
                                 "id": "DRAFT",
                                 "name": "Черновик"
                             },
                             "close_dt": "2024-06-10T16:00:00",
                             "contractors": []
                         }
                        """)
                );
    }
}
