package dev.septian.vaulthiveserver.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.septian.vaulthiveserver.TestData;
import dev.septian.vaulthiveserver.domain.ListEntity;
import dev.septian.vaulthiveserver.domain.ListGameEntity;
import dev.septian.vaulthiveserver.services.ListService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ListControllerIt {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ListService listService;

    @Autowired
    public ListControllerIt(MockMvc mockMvc, ListService listService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.listService = listService;
    }

    @Test
    public void testThatCreateListReturnsHttpStatus201Created() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        ListGameEntity item1 = TestData.createlistGameEntity(2345);
        ListGameEntity item2 = TestData.createlistGameEntity(2346);
        ListGameEntity item3 = TestData.createlistGameEntity(3345);

        listEntity.add(item1);
        listEntity.add(item2);
        listEntity.add(item3);

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lists/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson)).andExpect(
                        MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateListReturnsCreatedList() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        ListGameEntity item1 = TestData.createlistGameEntity(2345);
        ListGameEntity item2 = TestData.createlistGameEntity(2346);
        ListGameEntity item3 = TestData.createlistGameEntity(3345);

        listEntity.add(item1);
        listEntity.add(item2);
        listEntity.add(item3);

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lists/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson)).andExpect(
                        MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("A pieceful day"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames[0].gameId").value(2345))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames[1].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames[1].gameId").value(2346))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames[2].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames[2].gameId").value(3345));
    }

}
