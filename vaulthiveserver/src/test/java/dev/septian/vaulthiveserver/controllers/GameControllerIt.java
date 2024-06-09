package dev.septian.vaulthiveserver.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.septian.vaulthiveserver.services.GameService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class GameControllerIt {

    private MockMvc mockMvc;
    private GameService gameService;

    @Autowired
    public GameControllerIt(MockMvc mockMvc, GameService gameService) {
        this.mockMvc = mockMvc;
        this.gameService = gameService;
    }

    @Test
    public void testThatGameDetailsReturnHttpStatus200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/games/3498"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatSearchGameWithParamsReturnHttpStatus200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/games?search=batman"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatSearchGameReturnSearchGameDto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/games?search=batman"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
    
}
