package com.library.librarymgtsystem;

import com.library.librarymgtsystem.controller.PatronBookBorrowController;
import com.library.librarymgtsystem.model.PatronBookBorrow;
import com.library.librarymgtsystem.service.PatronBookBorrowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BorrowControllerTests {

    @MockBean
    private PatronBookBorrowService patronBookBorrowService;

    @Autowired
    private PatronBookBorrowController patronBookBorrowController;

    @Test
    void testBorrowBookReturnsStatusCode200(){
        //Given
        UUID bookId = UUID.randomUUID();
        UUID patronId = UUID.randomUUID();

        var borrowedBook = new PatronBookBorrow();

        when(patronBookBorrowService.borrowBook(bookId,patronId)).thenReturn(borrowedBook);

        //when
       var response = patronBookBorrowController.borrowBook(bookId,patronId);

        //then
        assertNotNull(response);
        assertEquals(borrowedBook,response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());

    }

    @Test
    void testReturnBookReturnsStatusCode200(){
        //Given
        UUID bookId = UUID.randomUUID();
        UUID patronId = UUID.randomUUID();

        var borrowedBook = new PatronBookBorrow();

        var successfulMessage = "Returned book successfully";

        when(patronBookBorrowService.returnBook(bookId,patronId)).thenReturn(successfulMessage);

        //when
        var response = patronBookBorrowController.returnBook(bookId,patronId);

        //then
        assertNotNull(response);
        assertEquals(successfulMessage,response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }
}
