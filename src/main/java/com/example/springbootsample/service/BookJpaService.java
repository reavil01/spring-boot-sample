package com.example.springbootsample.service;

import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookPatchRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.entity.Book;
import com.example.springbootsample.repository.BookRepository;
import kotlin._Assertions;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNullElse;

@Service
public class BookJpaService {
    private final BookRepository repository;

    public BookJpaService(final BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public BookResponse findById(long id) {
        Book book = repository.findById(id).orElseThrow();
        BookResponse response = new BookResponse(book);

        return response;
    }

    public long save(BookCreateRequest request) {
        Book book = repository.save(request.toEntity());

        return book.getId();
    }

    public long update(long id, BookUpdateRequest request) {
        Book saved = repository.findById(id).orElseThrow();
        Book updated = new Book(
                saved.getId(),
                request.getName(),
                request.getPrice(),
                request.getReleaseDate()
        );
        repository.save(updated);

        return id;
    }

    public long patch(long id, BookPatchRequest request) {
        Book saved = repository.findById(id).orElseThrow();
        Book updated = new Book(
                saved.getId(),
                requireNonNullElse(request.getName(), saved.getName()),
                requireNonNullElse(request.getPrice(), saved.getPrice()),
                requireNonNullElse(request.getReleaseDate(), saved.getReleaseDate())
        );

        return repository.save(updated).getId();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

//    private Book toEntity(BookCreateRequest request) {
//        return new Book(request.getName(), request.getPrice(), request.getReleaseDate());
//    }
}
