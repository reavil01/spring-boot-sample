package com.example.springbootsample.controller;

import com.example.springbootsample.dto.BookCreateRequest;
import com.example.springbootsample.dto.BookPatchRequest;
import com.example.springbootsample.dto.BookResponse;
import com.example.springbootsample.dto.BookUpdateRequest;
import com.example.springbootsample.service.BookJpaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {
    private BookJpaService bookService;

    public BookController(final BookJpaService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    BookResponse findById(@PathVariable("id") final String id) {
        long longId = Long.parseLong(id);
        return bookService.findById(longId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    long create(@RequestBody final BookCreateRequest bookCreateRequest) {
        return bookService.save(bookCreateRequest);
    }

    @PutMapping("/{id}")
    long update(
            @PathVariable("id") final long id,
            @RequestBody final BookUpdateRequest bookUpdateRequest
    ) {
        return bookService.update(id, bookUpdateRequest);
    }

    @PatchMapping("/{id}")
    long patch(
            @PathVariable("id") final long id,
            @RequestBody final BookPatchRequest bookPatchRequest
    ) {
        return bookService.patch(id, bookPatchRequest);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") final long id) {
        bookService.delete(id);
    }
}
