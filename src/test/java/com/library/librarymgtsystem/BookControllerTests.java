package com.library.librarymgtsystem;

import com.library.librarymgtsystem.controller.BookController;
import com.library.librarymgtsystem.model.Book;
import com.library.librarymgtsystem.service.BookService;
import com.library.librarymgtsystem.serviceimpl.BookServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTests {

    @MockBean
    private BookService bookService;

    @Autowired
    private BookController bookController;

    @Test
    void testGetAllBooksReturnStatusCode200() {
        //Given
        List<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setId(UUID.randomUUID());
        book1.setTitle("Book 1");
        books.add(book1);
        when(bookService.findAllBooks()).thenReturn(books);

        //when
        var response = bookController.getBooks(null);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).findAllBooks();

    }

    @Test
    void testGetBooksByIdReturnOnlyOne(){
        //Given
        Book book1 = new Book();
        book1.setId(UUID.randomUUID());
        book1.setTitle("Book 1");

        when(bookService.findBookById(book1.getId())).thenReturn(book1);

        //when
        var response = bookController.getBooks(book1.getId());

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book1,response.getBody());

    }

    @Test
    void testAddNewBookReturnStatusCode201() {
        //Given
        Book book1 = new Book();
        book1.setId(UUID.randomUUID());
        book1.setTitle("Book 1");

        when(bookService.addBook(book1)).thenReturn(book1);

        //when
        var response = bookController.addBook(book1);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(book1,response.getBody());

    }

    @Test
    void testNotFoundException() {
        // Given
        UUID randomId = UUID.randomUUID();
        when(bookService.findBookById(randomId)).thenThrow(EntityNotFoundException.class);

        // When and Then
        assertThrows(EntityNotFoundException.class, () -> {
            bookController.getBooks(randomId);
        });



    }


    @Test
    void testUpdateBookReturnStatusCode200() {
        //Given
        var randomId = UUID.randomUUID();
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");

        when(bookService.updateBook(book1,randomId)).thenReturn(book1);

        //when
        var response = bookController.updateBook(book1,randomId);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());


    }

}
