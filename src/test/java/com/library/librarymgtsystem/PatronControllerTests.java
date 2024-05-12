package com.library.librarymgtsystem;

import com.library.librarymgtsystem.controller.PatronController;
import com.library.librarymgtsystem.model.Patron;
import com.library.librarymgtsystem.service.PatronService;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatronControllerTests {

    @MockBean
    private PatronService patronService;

    @Autowired
    PatronController patronController;

    @Test
    void testGetAllBooksReturnStatusCode200() {
        //Given
        List<Patron> patrons = new ArrayList<>();
        Patron patron = new Patron();
        patron.setId(UUID.randomUUID());
        patron.setName("Patron 1");

        patrons.add(patron);
        when(patronService.findAllPatrons()).thenReturn(patrons);

        //when
        var response = patronController.getPatrons(null);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patrons, response.getBody());
        verify(patronService, times(1)).findAllPatrons();

    }

    @Test
    void testAddPatronReturnEntityExistsException() {
        //Given
        Patron patron = new Patron();
        patron.setId(UUID.randomUUID());
        patron.setName("Patron 1");

        when(patronService.addPatron(patron)).thenThrow(EntityExistsException.class);

        //when and then
        assertThrows(EntityExistsException.class, () -> patronController.addPatron(patron));

    }

    @Test
    void testGetBookByIdReturnsASingleBookObject() {
        //Given
        var randomId = UUID.randomUUID();
        Patron patron = new Patron();
        patron.setName("Patron 1");

        when(patronService.findPatronById(randomId)).thenReturn(patron);

        //when
        var response = patronController.getPatrons(randomId);

        //then
        assertNotNull(response);
        assertEquals(patron, response.getBody());

    }


}
