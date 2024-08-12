package com.chiniakin.controller;

import com.chiniakin.TestBeans;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class RequestWithoutSignInTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void saveContractorWithoutSignInShouldBeForbidden() throws Exception {
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
    void addContractorToRoleWithoutSignInShouldBeForbidden() throws Exception {
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
    void deleteContractorToRoleWithoutSignInShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/contractor-to-role/delete/{id}", "6e6a086f-5e74-49b4-b82b-99135aa4b555"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void getDealByIdWithoutSignInShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deal/{id}", "c3d5b726-92e8-4bb4-9826-66f72fce1c4d"))
                .andExpect(status().isForbidden());
    }

    @Test
    void saveDealModelWithoutSignInShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deal/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                      {
                                        "id": "c3d5b726-92e8-4bb4-9826-66f72fce1c4d",
                                        "description": "Описание сделки",
                                        "agreement_number": "12345",
                                        "agreement_date": "2023-07-19",
                                        "agreement_start_dt": "2023-07-19T10:00:00",
                                        "availability_date": "2023-12-31",
                                        "type": {
                                          "id":"CREDIT",
                                          "name":"Кредитная сделка"
                                        },
                                        "sum": 2000.00,
                                        "close_dt": "2023-07-19T10:00:00"
                                      }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    void searchDealWithoutSignInShouldBeForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deal/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                        "page": 1,
                                        "size": 3,
                                        "inn": "123"
                                  }

                                """
                        ))
                .andExpect(status().isForbidden());

    }
}
