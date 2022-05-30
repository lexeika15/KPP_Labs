package com.example.laba_1;


import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class Laba1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage_1() throws Exception {
        this.mockMvc.perform(get("/convert").param("number", "1244124")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("100101111101111011100")));
    }

    @Test
    public void shouldReturnDefaultMessage_2() throws Exception {
        this.mockMvc.perform(get("/convert").param("number", "234")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("11101010")));
    }

    @Test
    public void shouldReturnDefaultMessageLessThenZero() throws Exception {
        this.mockMvc.perform(get("/convert").param("number", "-234")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("-11101010")));
    }

    @Test
    public void shouldReturnTypeMismatchException() throws Exception {
        this.mockMvc.perform(get("/convert").param("number", "fgfgfg"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    public void shouldReturnMissingServletRequestParameterException() throws Exception {
        this.mockMvc.perform(get("/convert"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MissingServletRequestParameterException));
    }

    @Test
    public void shouldReturnNoHandlerFoundException() throws Exception {
        this.mockMvc.perform(get("/convt"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoHandlerFoundException));
    }
}
