package com.traffic.member.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    AutoCloseable openMocks;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
    }
    @Test
    void signUp() throws Exception {


    }

    @Test
    void signIn() {
    }

    @Test
    void naverLoginUrl() throws Exception {
        // when & then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/member/naver")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void naverAccessToken() {
    }
}