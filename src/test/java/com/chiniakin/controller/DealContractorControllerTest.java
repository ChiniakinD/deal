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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestBeans.class)
public class DealContractorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("/insertForTests/insert.sql")
    public void saveContractorShouldSuccessfulSaved() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deal-contractor/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                        {
                                           "id": "561b5244-29ed-4d18-9145-193775a1d0dc",
                                           "deal_id": "c3d5b726-92e8-4bb4-9826-66f72fce1c4d",
                                           "contractor_id": "1233",
                                           "name": "123",
                                           "main": "true"
                                         }
                                        """
                        ))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("/insertForTests/insert.sql")
    public void deleteContractorShouldSuccessfulDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/deal-contractor/delete/{id}", "8a7b9a6e-b60b-4a6f-951d-8b6b56a2f8ec"))
                .andExpect(status().isOk());
    }
}
