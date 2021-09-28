package com.example.springbootsample.spring.service;

import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookPatchRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.entity.Book;
import com.example.springbootsample.service.BookDataService;
import com.example.springbootsample.spring.entity.BookEntity;
import com.example.springbootsample.spring.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNullElse;

@Service
public class BookJpaDataService implements BookDataService {
    private final BookRepository repository;

    public BookJpaDataService(final BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    @Override
    public long save(BookCreateRequest request) {
        BookEntity bookEntity = new BookEntity(
                request.getName(),
                request.getPrice(),
                request.getReleaseDate()
        );
        BookEntity saved = repository.save(bookEntity);

        return saved.getId();
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public long update(long id, BookUpdateRequest request) {
        BookEntity saved = repository.findById(id).orElseThrow();
        BookEntity updated = new BookEntity(
                saved.getId(),
                request.getName(),
                request.getPrice(),
                request.getReleaseDate()
        );
        repository.save(updated);

        return id;
    }

    @Override
    public long patch(long id, BookPatchRequest request) {
        BookEntity saved = repository.findById(id).orElseThrow();
        BookEntity updated = new BookEntity(
                saved.getId(),
                requireNonNullElse(request.getName(), saved.getName()),
                requireNonNullElse(request.getPrice(), saved.getPrice()),
                requireNonNullElse(request.getReleaseDate(), saved.getReleaseDate())
        );

        return repository.save(updated).getId();
    }

    @Override
    public Optional<BookResponse> findById(long id) {
        Optional<BookEntity> bookEntity = repository.findById(id);

        Optional<BookResponse> response = null;
        if (bookEntity.isPresent()) {
            response = Optional.of(new BookResponse(bookEntity.get()));
        }

        return response;
    }

    @Override
    public List<Book> selectAll() {
        List<BookEntity> bookEntities = repository.findAll();

        final List<Book> books = new ArrayList<>();
        for (BookEntity bookEntity : bookEntities) {
            books.add(toBook(bookEntity));
        }
        return books;
    }

    private Book toBook(BookEntity bookEntity) {
        return new Book(
                bookEntity.getId(),
                bookEntity.getName(),
                bookEntity.getPrice(),
                bookEntity.getReleaseDate()
        );
    }
}
