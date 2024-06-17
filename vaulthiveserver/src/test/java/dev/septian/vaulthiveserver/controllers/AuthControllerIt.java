package dev.septian.vaulthiveserver.controllers;

import dev.septian.vaulthiveserver.domain.dtos.LoginDto;
import dev.septian.vaulthiveserver.domain.dtos.RegisterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIt {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthControllerIt(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testRegister() throws Exception {
        RegisterDto registerDto = RegisterDto.builder()
                .username("user1")
                .email("user1@example.com")
                .password("password")
                .build();

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAuthenticate() throws Exception {
        LoginDto loginDto = LoginDto.builder()
                .username("user1")
                .password("password")
                .build();

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk());
    }
}