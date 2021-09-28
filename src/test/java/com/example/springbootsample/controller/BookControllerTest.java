package com.example.springbootsample.controller;

import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.entity.Book;
import com.example.springbootsample.service.BookJpaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BookControllerTest {
    @MockBean
    private BookJpaService service;

    @Autowired
    @InjectMocks
    private BookController controller;

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
        System.out.println(json);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteTest() throws Exception {
        // given
        String name = "book";
        int price = 1000;
        LocalDate releaseDate = LocalDate.now();
        BookCreateRequest request = new BookCreateRequest(name, price, releaseDate);
        String json = mapper.writeValueAsString(request);
        System.out.println(json);

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
