package com.kelly.springbootkelly.web;

import com.kelly.springbootkelly.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_will_return() throws Exception {
        String hello = "hello";
        RequestBuilder builder = MockMvcRequestBuilders.get("/hello");
        mvc.perform(builder)
                .andExpect(content().string(hello))
                .andExpect(status().isOk());
    }

    @Test
    public void helloDto_will_return() throws  Exception {
        String name = "hello";
        int amount = 1000;

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(HelloResponseDto.class).build();
        RequestBuilder request = MockMvcRequestBuilders.get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.amount").value(amount));
    }
}
