package com.team33.backend.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    private MockMvc mockMvc;

    @InjectMocks
    private StubController stubController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(stubController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    void 사용자예외가_발생하면_핸들러에서_처리한다() throws Exception {
        // when
        ResultActions performResult = mockMvc.perform(get("/test/error/user-exception"));

        // then
        performResult.andExpect(status().isBadRequest());
    }

    @Test
    void 처리하지_않은_예외가_발생하면_핸들러에서_처리한다() throws Exception {
        // when
        ResultActions performResult = mockMvc.perform(get("/test/error/exception"));

        // then
        performResult.andExpect(status().isInternalServerError());
    }

    @RestController
    static class StubController {

        @GetMapping("/test/error/user-exception")
        public void userException() {
            throw new TestException();
        }

        @GetMapping("/test/error/exception")
        public void exception() {
            throw new RuntimeException();
        }
    }

    static class TestException extends IssueTrackerRuntimeException {

        public TestException() {
            super("예외가 발생했습니다.");
        }

        @Override
        protected String getErrorCode() {
            return "TEST_ERROR_CODE";
        }

        @Override
        protected HttpStatus getStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    }

}