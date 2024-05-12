package com.library.librarymgtsystem.repository;

import com.library.librarymgtsystem.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatronRepository extends JpaRepository<Patron, UUID> {

    Optional<Patron> findByEmail(String email);

}
