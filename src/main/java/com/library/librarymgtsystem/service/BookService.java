package com.library.librarymgtsystem.service;

import com.library.librarymgtsystem.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

    Book findBookById(UUID id);

    Book addBook(Book book);

    Book updateBook(Book book, UUID id);

    void deleteBook(UUID id);

    List<Book> findAllBooks();
}
