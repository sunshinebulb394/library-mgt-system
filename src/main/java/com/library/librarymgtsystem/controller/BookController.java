package com.library.librarymgtsystem.controller;

import com.library.librarymgtsystem.model.Book;
import com.library.librarymgtsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        log.info("inside POST api -> /api/book");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable UUID id) {
        log.info("inside PUT api -> /api/book");
        return ResponseEntity.ok(bookService.updateBook(book,id));
    }

    @GetMapping({"/{id}",""})
    public ResponseEntity<?> getBooks(@PathVariable(required = false) UUID id) {
        log.info("inside GET api -> /api/book/{id}");

        if (id != null) {
            return ResponseEntity.ok(bookService.findBookById(id));
        }
        return ResponseEntity.ok(bookService.findAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooks(@PathVariable UUID id) {
        log.info("inside DELETE api -> /api/book/{id}");

        bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");

    }
}
