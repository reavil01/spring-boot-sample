package com.example.springbootsample.service;

import com.example.springbootsample.dto.BookPatchRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.spring.entity.BookEntity;
import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.spring.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookEntityJpaServiceTest {
    @Autowired
    private BookDataService service;
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
        BookEntity saved = saveBook();

        // when
        BookResponse response = service.findById(saved.getId()).orElseThrow();

        // then
        compareAllValues(saved, response.getId(), response.getName(), response.getPrice(), response.getReleaseDate());
    }


    @Test
    void saveRequestTest() {
        // given
        BookCreateRequest request = getBookSaveDtoInstance();

        // when
        long savedId = service.save(request);
        BookEntity saved = repository.findById(savedId).orElseThrow();

        // then
        compareAllValues(saved, savedId, name, price, releaseDate);
    }

    @Test
    void updateRequestTest() {
        // given
        BookEntity saved = saveBook();
        String newName = "updated";
        int newPrice = 10000;
        LocalDate newReleaseDate = LocalDate.now().minusDays(3);
        BookUpdateRequest request = new BookUpdateRequest(newName, newPrice, newReleaseDate);

        // when
        service.update(saved.getId(), request);

        // then
        BookEntity updated = repository.findById(saved.getId()).orElseThrow();
        compareAllValues(updated, saved.getId(), newName, newPrice, newReleaseDate);
    }

    @Test
    void patchRequestTest() {
        // given
        BookEntity saved = saveBook();
        String newName = "updated";
        BookPatchRequest request = new BookPatchRequest(newName, null, null);

        // when
        service.patch(saved.getId(), request);

        // then
        BookEntity updated = repository.findById(saved.getId()).orElseThrow();
        compareAllValues(updated, saved.getId(), newName, saved.getPrice(), saved.getReleaseDate());
    }

    @Test
    void deleteRequestTest() {
        // given
        BookEntity saved = saveBook();
        assertThat(repository.findAll().size()).isEqualTo(1);

        // when
        service.delete(saved.getId());

        // then
        assertThat(repository.findAll().size()).isEqualTo(0);
    }

    private void compareAllValues(BookEntity bookEntity, long id, String name, int price, LocalDate releaseDate) {
        assertThat(bookEntity.getId()).isEqualTo(id);
        assertThat(bookEntity.getName()).isEqualTo(name);
        assertThat(bookEntity.getPrice()).isEqualTo(price);
        assertThat(bookEntity.getReleaseDate()).isEqualTo(releaseDate);
    }

    private BookEntity saveBook() {
        long id = service.save(getBookSaveDtoInstance());
        return repository.findById(id).orElseThrow();
    }

    private BookCreateRequest getBookSaveDtoInstance() {
        return new BookCreateRequest(name, price, releaseDate);
    }
}
