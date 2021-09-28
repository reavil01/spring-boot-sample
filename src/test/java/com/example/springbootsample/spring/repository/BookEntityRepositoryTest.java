package com.example.springbootsample.spring.repository;

import com.example.springbootsample.spring.entity.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookEntityRepositoryTest {
    @Autowired private BookRepository repository;

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
        BookEntity bookEntity = getBookInstance();

        // when
        BookEntity saved = repository.save(bookEntity);

        // then
        compareAllValues(saved, saved.getId(), name, price, releaseDate);
    }

    @Test
    void deleteTest() {
        // given
        BookEntity saved = saveBook();

        // when
        repository.delete(saved);

        // then
        assertThat(repository.findAll().size()).isEqualTo(0);
    }

    @Test
    void updateTest() {
        // given
        BookEntity saved = saveBook();
        String newName = "changed";
        int newPrice = 11111;
        LocalDate newReleaseDate = LocalDate.now().minusDays(3);
        BookEntity updateEntity = new BookEntity(saved.getId(), newName, newPrice, newReleaseDate);

        // when
        repository.save(updateEntity);

        // then
        BookEntity updated = repository.findById(saved.getId()).orElseThrow();
        compareAllValues(updated, saved.getId(), newName, newPrice, newReleaseDate);
    }

    private void compareAllValues(BookEntity bookEntity, long id, String name, int price, LocalDate releaseDate) {
        assertThat(bookEntity.getId()).isEqualTo(id);
        assertThat(bookEntity.getName()).isEqualTo(name);
        assertThat(bookEntity.getPrice()).isEqualTo(price);
        assertThat(bookEntity.getReleaseDate()).isEqualTo(releaseDate);
    }

    private BookEntity saveBook() {
        return repository.save(getBookInstance());
    }

    private BookEntity getBookInstance() {
        return new BookEntity(name, price, releaseDate);
    }
}
