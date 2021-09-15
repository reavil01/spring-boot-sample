package com.example.springbootsample.service;

import com.example.springbootsample.dto.BookResponseDto;
import com.example.springbootsample.entity.Book;
import com.example.springbootsample.dto.BookSaveDto;
import com.example.springbootsample.dto.BookUpdateDto;
import com.example.springbootsample.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookJpaServiceTest {
    @Autowired
    private BookJpaService service;
    @Autowired
    private BookRepository repository;

    String name = "request";
    int price = 1000;
    LocalDate releaseDate = LocalDate.now();

    @BeforeEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void findRequestTest() {
        // given
        Book saved = saveBook();

        // when
        BookResponseDto reponse = service.findById(saved.getId());

        // then
        compareAllValues(saved, reponse.getId(), reponse.getName(), reponse.getPrice(), reponse.getReleaseDate());
    }


    @Test
    void saveRequestTest() {
        // given
        BookSaveDto request = getBookSaveDtoInstance();

        // when
        long savedId = service.save(request);
        Book saved = repository.findById(savedId).orElseThrow();

        // then
        compareAllValues(saved, savedId, name, price, releaseDate);
    }

    @Test
    void updateRequestTest() {
        // given
        Book saved = saveBook();
        String newName = "updated";
        int newPrice = 10000;
        LocalDate newReleaseDate = LocalDate.now().minusDays(3);
        BookUpdateDto request = new BookUpdateDto(newName, newPrice, newReleaseDate);

        // when
        service.update(saved.getId(), request);

        // then
        Book updated = repository.findById(saved.getId()).orElseThrow();
        compareAllValues(updated, saved.getId(), newName, newPrice, newReleaseDate);
    }

    private void compareAllValues(Book book, long id, String name, int price, LocalDate releaseDate) {
        assertThat(book.getId()).isEqualTo(id);
        assertThat(book.getName()).isEqualTo(name);
        assertThat(book.getPrice()).isEqualTo(price);
        assertThat(book.getReleaseDate()).isEqualTo(releaseDate);
    }

    private Book saveBook() {
        return repository.save(getBookSaveDtoInstance().toEntity());
    }

    private BookSaveDto getBookSaveDtoInstance() {
        return new BookSaveDto(name, price, releaseDate);
    }
}
