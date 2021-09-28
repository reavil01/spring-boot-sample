package com.example.springbootsample.service;

import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookPatchRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDataService {

    long save(BookCreateRequest bookCreateRequest);

    void delete(long id);

    long update(long id, BookUpdateRequest bookUpdateRequest);

    long patch(long id, BookPatchRequest bookPatchRequest);

    Optional<BookResponse> findById(long id);

    List<Book> selectAll();

}
