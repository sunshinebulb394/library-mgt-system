package com.library.librarymgtsystem.repository;


import com.library.librarymgtsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Optional<Book> findByISBN(String isbn);
}
