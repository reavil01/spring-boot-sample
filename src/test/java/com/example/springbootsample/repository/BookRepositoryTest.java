package com.example.springbootsample.repository;

import com.example.springbootsample.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    String name = "test";
    int price = 1000;
    LocalDate releaseDate = LocalDate.now();

    @BeforeEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void saveTest() {
        // given
        Book book = getBookInstance();

        // when
        Book saved = repository.save(book);

        // then
        compareAllValues(saved, saved.getId(), name, price, releaseDate);
    }

    @Test
    void removeTest() {
        // given
        Book saved = saveBook();

        // when
        repository.delete(saved);

        // then
        assertThat(repository.findAll().size()).isEqualTo(0);
    }

    @Test
    void updateTest() {
        // given
        Book saved = saveBook();
        String newName = "changed";
        int newPrice = 11111;
        LocalDate newReleaseDate = LocalDate.now().minusDays(3);

        // when
        saved.setName(newName);
        saved.setPrice(newPrice);
        saved.setReleaseDate(newReleaseDate);

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
        return repository.save(getBookInstance());
    }

    private Book getBookInstance() {
        return new Book(name, price, releaseDate);
    }
}
