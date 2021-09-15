package com.example.springbootsample.service;

import com.example.springbootsample.entity.Book;
import com.example.springbootsample.dto.BookResponseDto;
import com.example.springbootsample.dto.BookSaveDto;
import com.example.springbootsample.dto.BookUpdateDto;
import com.example.springbootsample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookJpaService {
    private final BookRepository repository;

    public BookResponseDto findById(long id) {
        Book book = repository.findById(id).orElseThrow();
        BookResponseDto response = new BookResponseDto(book);

        return response;
    }

    public long save(BookSaveDto request) {
        Book book = repository.save(request.toEntity());

        return book.getId();
    }

    public long update(long id, BookUpdateDto request) {
        Book saved = repository.findById(id).orElseThrow();
        saved.update(request);
        repository.save(saved);

        return id;
    }
}
