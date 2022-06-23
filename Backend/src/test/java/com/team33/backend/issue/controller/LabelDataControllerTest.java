package com.team33.backend.issue.controller;

import com.team33.backend.issue.service.LabelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class LabelDataControllerTest {

    private MockMvc mockMvc;

    private LabelService memberService;

//    @Test
//    @DisplayName("dd")
//    void m() throws Exception {
//        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/issuegroup/1/labels/1"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }

}
