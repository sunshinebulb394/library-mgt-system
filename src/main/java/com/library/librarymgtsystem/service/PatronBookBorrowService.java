package com.library.librarymgtsystem.service;

import com.library.librarymgtsystem.model.PatronBookBorrow;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PatronBookBorrowService {

    String borrowBook(UUID bookId, UUID patronId);
    String returnBook(UUID bookId, UUID patronId);
}
