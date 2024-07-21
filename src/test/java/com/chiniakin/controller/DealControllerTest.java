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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class DealControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/insertForTests/insert.sql")
    public void getDealByIdShouldReturnCorrectDealModel() throws Exception {
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

    @Test
    public void changeStatusShouldCorrectChangeStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/deal/change/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "deal_id": "e6c2296c-28a4-4e12-8a59-3ad1e0e4f6a3",
                            "deal_status_id": "ACTIVE"
                        }
                        """));
        mockMvc.perform(MockMvcRequestBuilders.get("/deal/{id}", "e6c2296c-28a4-4e12-8a59-3ad1e0e4f6a3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.id").value("ACTIVE"));
    }

    @Test
    public void saveDealModelShouldBeSuccessfulSaved() throws Exception {
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
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    public void searchDealShouldReturnCorrectData() throws Exception {
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
                .andExpect(content().json("""
                          {
                              "totalElements": 2,
                              "totalPages": 1,
                              "pageable": {
                                  "pageNumber": 0,
                                  "pageSize": 3,
                                  "sort": {
                                      "sorted": false,
                                      "empty": true,
                                      "unsorted": true
                                  },
                                  "offset": 0,
                                  "paged": true,
                                  "unpaged": false
                              },
                              "first": true,
                              "last": true,
                              "size": 3,
                              "content": [
                                  {
                                      "id": "d3b07384-d9a4-4a4a-b1de-5c2e5dcf930d",
                                      "description": "Сделка по продаже оборудования",
                                      "sum": 100000.00,
                                      "agreement_number": "AG123456",
                                      "agreement_date": "2024-01-15",
                                      "agreement_start_dt": "2024-01-20T09:00:00",
                                      "availability_date": "2025-01-15",
                                      "type": {
                                          "id": "CREDIT",
                                          "name": "Кредитная сделка"
                                      },
                                      "status": {
                                          "id": "ACTIVE",
                                          "name": "Утвержденная"
                                      },
                                      "close_dt": null,
                                      "contractors": [
                                          {
                                              "id": "6e6a086f-5e74-49b4-b82b-99135aa4b555",
                                              "contractorID": "2",
                                              "name": "Контрагент 2",
                                              "main": true,
                                              "roles": null
                                          },
                                          {
                                              "id": "8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec",
                                              "contractorID": "1",
                                              "name": "Контрагент 1",
                                              "main": false,
                                              "roles": null
                                          }
                                      ]
                                  },
                                  {
                                      "id": "d3b07384-d9a4-4a4a-b1de-5c2e5dcf930d",
                                      "description": "Сделка по продаже оборудования",
                                      "sum": 100000.00,
                                      "agreement_number": "AG123456",
                                      "agreement_date": "2024-01-15",
                                      "agreement_start_dt": "2024-01-20T09:00:00",
                                      "availability_date": "2025-01-15",
                                      "type": {
                                          "id": "CREDIT",
                                          "name": "Кредитная сделка"
                                      },
                                      "status": {
                                          "id": "ACTIVE",
                                          "name": "Утвержденная"
                                      },
                                      "close_dt": null,
                                      "contractors": [
                                          {
                                              "id": "6e6a086f-5e74-49b4-b82b-99135aa4b555",
                                              "contractorID": "2",
                                              "name": "Контрагент 2",
                                              "main": true,
                                              "roles": null
                                          },
                                          {
                                              "id": "8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec",
                                              "contractorID": "1",
                                              "name": "Контрагент 1",
                                              "main": false,
                                              "roles": null
                                          }
                                      ]
                                  }
                              ],
                              "number": 0,
                              "sort": {
                                  "sorted": false,
                                  "empty": true,
                                  "unsorted": true
                              },
                              "numberOfElements": 2,
                              "empty": false
                          }  
                        """)
                );
    }
}
