package dev.septian.vaulthiveserver.controllers;

import java.util.HashSet;
import java.util.Set;

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
import dev.septian.vaulthiveserver.domain.entities.GameEntity;
import dev.septian.vaulthiveserver.domain.entities.ListEntity;
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

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lists/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson)).andExpect(
                        MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testThatCreateListReturnsCreatedList() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        Set<GameEntity> listGames = new HashSet<>();
        listGames.add(TestData.createGameEntity(1));
        listGames.add(TestData.createGameEntity(2));
        listGames.add(TestData.createGameEntity(3));

        listEntity.setGames(listGames);

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/lists/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson))
                .andExpect(
                        MockMvcResultMatchers.status().isCreated())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value("A pieceful day"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.description").value("For those who want to relax"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.games").isArray());
    }

    @Test
    public void testThatGetListsReturnsHttpStatus200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetListsReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void testThatGetListsReturnsList() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        listEntity.getGames().add(TestData.createGameEntity(2));
        listEntity.getGames().add(TestData.createGameEntity(3));
        listEntity.getGames().add(TestData.createGameEntity(4));

        listService.save(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("A pieceful day"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].games").isArray());
    }

    @Test
    public void testThatUpdateListReturnsHttpStatus200Ok() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        // ListGameEntity item1 = TestData.createlistGameEntity(2345);
        // ListGameEntity item2 = TestData.createlistGameEntity(2346);
        // ListGameEntity item3 = TestData.createlistGameEntity(3345);

        // listEntity.add(item1);
        // listEntity.add(item2);
        // listEntity.add(item3);

        listService.save(listEntity);

        listEntity.setTitle("A pieceful night");

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/lists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson)).andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatUpdateListReturnsUpdatedList() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        // ListGameEntity item1 = TestData.createlistGameEntity(2345);
        // ListGameEntity item2 = TestData.createlistGameEntity(2346);
        // ListGameEntity item3 = TestData.createlistGameEntity(3345);

        // listEntity.add(item1);
        // listEntity.add(item2);
        // listEntity.add(item3);

        listService.save(listEntity);

        listEntity.setTitle("A pieceful night");
        listEntity.setDescription("A pieceful night with a pieceful game");

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/lists/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson))
                .andExpect(
                        MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.title").value("A pieceful night"))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.description").value("A pieceful night with a pieceful game"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listGames").isArray());
    }

    @Test
    public void testThatIpdateListReturnsHttpStatus404NotFound() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        // ListGameEntity item1 = TestData.createlistGameEntity(2345);
        // ListGameEntity item2 = TestData.createlistGameEntity(2346);
        // ListGameEntity item3 = TestData.createlistGameEntity(3345);

        // listEntity.add(item1);
        // listEntity.add(item2);
        // listEntity.add(item3);

        listService.save(listEntity);

        listEntity.setTitle("A pieceful night");

        String listJson = objectMapper.writeValueAsString(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/lists/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(listJson)).andExpect(
                        MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetListReturnsHttpStatus200Ok() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        // ListGameEntity item1 = TestData.createlistGameEntity(2345);
        // ListGameEntity item2 = TestData.createlistGameEntity(2346);
        // ListGameEntity item3 = TestData.createlistGameEntity(3345);

        // listEntity.add(item1);
        // listEntity.add(item2);
        // listEntity.add(item3);

        listService.save(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetListReturnsHttpStatus404NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatDeleteListReturnsHttpStatus204NoContent() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        // ListGameEntity item1 = TestData.createlistGameEntity(2345);
        // ListGameEntity item2 = TestData.createlistGameEntity(2346);
        // ListGameEntity item3 = TestData.createlistGameEntity(3345);

        // listEntity.add(item1);
        // listEntity.add(item2);
        // listEntity.add(item3);

        listService.save(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteListReturnsHttpStatus404NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatDeleteListActuallyDeletes() throws Exception {
        ListEntity listEntity = TestData.createListEntityA();
        // ListGameEntity item1 = TestData.createlistGameEntity(2345);
        // ListGameEntity item2 = TestData.createlistGameEntity(2346);
        // ListGameEntity item3 = TestData.createlistGameEntity(3345);

        // listEntity.add(item1);
        // listEntity.add(item2);
        // listEntity.add(item3);

        listService.save(listEntity);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lists/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
