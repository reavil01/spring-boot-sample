package com.example.springbootsample.service;

import com.example.springbootsample.dto.BookPatchRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.entity.Book;
import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookJpaServiceTest {
    @Autowired private BookJpaService service;
    @Autowired private BookRepository repository;

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
        BookResponse response = service.findById(saved.getId());

        // then
        compareAllValues(saved, response.getId(), response.getName(), response.getPrice(), response.getReleaseDate());
    }


    @Test
    void saveRequestTest() {
        // given
        BookCreateRequest request = getBookSaveDtoInstance();

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
        BookUpdateRequest request = new BookUpdateRequest(newName, newPrice, newReleaseDate);

        // when
        service.update(saved.getId(), request);

        // then
        Book updated = repository.findById(saved.getId()).orElseThrow();
        compareAllValues(updated, saved.getId(), newName, newPrice, newReleaseDate);
    }

    @Test
    void patchRequestTest() {
        // given
        Book saved = saveBook();
        String newName = "updated";
        BookPatchRequest request = new BookPatchRequest(newName, null, null);

        // when
        service.patch(saved.getId(), request);

        // then
        Book updated = repository.findById(saved.getId()).orElseThrow();
        compareAllValues(updated, saved.getId(), newName, saved.getPrice(), saved.getReleaseDate());
    }

    @Test
    void deleteRequestTest() {
        // given
        Book saved = saveBook();
        assertThat(repository.findAll().size()).isEqualTo(1);

        // when
        service.delete(saved.getId());

        // then
        assertThat(repository.findAll().size()).isEqualTo(0);
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

    private BookCreateRequest getBookSaveDtoInstance() {
        return new BookCreateRequest(name, price, releaseDate);
    }
}
