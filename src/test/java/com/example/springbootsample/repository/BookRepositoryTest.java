package com.example.springbootsample.repository;

import com.example.springbootsample.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @Test
    void saveTest() {
        // given
        String name = "test";
        int price = 1000;
        LocalDate releaseDate = LocalDate.now();
        Book book = new Book(name, price, releaseDate);

        // when
        Book saved = repository.save(book);

        // then
        assertThat(saved.getId()).isEqualTo(1);
        assertThat(saved.getName()).isEqualTo(name);
        assertThat(saved.getPrice()).isEqualTo(price);
        assertThat(saved.getReleaseDate()).isEqualTo(releaseDate);
    }
}
