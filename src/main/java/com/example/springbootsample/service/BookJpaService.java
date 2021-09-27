package com.example.springbootsample.service;

import com.example.springbootsample.entity.Book;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.repository.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookJpaService {
    private final BookRepository repository;

    BookJpaService(final BookRepository bookRepository) {
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
        saved.update(request);
        repository.save(saved);

        return id;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
