package com.example.springbootsample.controller;

import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.entity.Book;
import com.example.springbootsample.service.BookJpaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
//@AutoConfigureWebMvc
public class BookControllerTest {
    @MockBean
    private BookJpaService service;

    @Autowired
    @InjectMocks
    private BookController controller;

    @Autowired
    private Gson gson;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getTest() throws Exception {
        // given
        String name = "book";
        int price = 1000;
        LocalDate releaseDate = LocalDate.now();
        Book book = new Book(name, price, releaseDate);

        // when
        when(service.findById(1L)).thenReturn(new BookResponse(book));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", name).exists())
                .andExpect(jsonPath("$.price", price).exists())
                .andExpect(jsonPath("$.releaseDate", releaseDate).exists());
    }

    @Test
    void creatTest() throws Exception {
        // given
        String name = "book";
        int price = 1000;
        LocalDate releaseDate = LocalDate.now();
        BookCreateRequest request = new BookCreateRequest(name, price, releaseDate);
        String json = mapper.writeValueAsString(request);
//        String json = gson.toJson(request);
        System.out.println(json);

        // then
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}
