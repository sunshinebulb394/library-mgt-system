package com.library.librarymgtsystem.service;

import com.library.librarymgtsystem.model.Patron;

import java.util.List;
import java.util.UUID;

public interface PatronService {
    Patron findPatronById(UUID id);

    Patron addPatron(Patron patron);
    List<Patron> findAllPatrons();

    Patron updatePatron(Patron patron,UUID id);

    void deletePatron(UUID id);

}
