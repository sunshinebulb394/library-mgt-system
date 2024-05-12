package com.library.librarymgtsystem.serviceimpl;

import com.library.librarymgtsystem.model.Book;
import com.library.librarymgtsystem.repository.BookRepository;
import com.library.librarymgtsystem.service.BookService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * this class is used implement crud functionality for the book service
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     *
     * @param id
     * @return Book
     */
    @Override
    public Book findBookById(UUID id) {
        log.info("inside findBookById method");
       return bookRepository.findById(id)
               .orElseThrow(()-> new EntityNotFoundException("Book Not Found"));

    }

    /**
     * find book by isbn,if it exists return an already exist message
     * @param book
     * @return Book
     */
    @Override
    public Book addBook(Book book) {
        log.info("inside addBook method");
        Optional<Book> existingBook = bookRepository.findByISBN(book.getISBN());
        if (existingBook.isPresent()) {
            throw new EntityExistsException("Book already exists");
        } else {
            return bookRepository.save(book);
        }
    }

    /**
     *
     * @param book
     * @param id
     * @return Book
     */
    @Override
    @Transactional
    public Book updateBook(Book book,UUID id) {
        log.info("inside updateBook method");

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book Not Found"));
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setPublicationYear(book.getPublicationYear());
        return bookRepository.save(existingBook);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deleteBook(UUID id) {
        log.info("inside deleteBook method");

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book Not Found"));
        bookRepository.delete(existingBook);

    }


    /**
     *
     * @return List Of Books
     */
    @Override
    @Cacheable("books")
    public List<Book> findAllBooks() {
        log.info("inside findAllBooks method");

        return bookRepository.findAll();
    }
}
