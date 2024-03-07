package com.example.seminar.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    

}
