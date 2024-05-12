package com.library.librarymgtsystem.controller;

import com.library.librarymgtsystem.model.PatronBookBorrow;
import com.library.librarymgtsystem.service.PatronBookBorrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/borrow")
@Slf4j
public class PatronBookBorrowController {
    private final PatronBookBorrowService patronBookBorrowService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable("bookId") UUID bookId, @PathVariable("patronId") UUID patronId) {
        log.info("inside POST api -> /api/borrow/{bookId}/patron/{patronId}");
        return ResponseEntity.status(HttpStatus.CREATED).body(patronBookBorrowService.borrowBook(bookId, patronId));
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable("bookId") UUID bookId, @PathVariable("patronId") UUID patronId) {
        log.info("inside PUT api -> /api/borrow/{bookId}/patron/{patronId}");

        return ResponseEntity.ok(patronBookBorrowService.returnBook(bookId, patronId));
    }
}
