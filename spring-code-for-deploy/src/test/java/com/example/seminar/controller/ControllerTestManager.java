package com.example.seminar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
@ActiveProfiles("test")
public class ControllerTestManager {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
}
