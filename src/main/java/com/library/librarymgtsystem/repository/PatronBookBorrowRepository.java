package com.library.librarymgtsystem.repository;

import com.library.librarymgtsystem.model.PatronBookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PatronBookBorrowRepository extends JpaRepository<PatronBookBorrow, Long> {

    @Query("SELECT pb FROM PatronBookBorrow pb WHERE pb.book.id = :bookId and pb.patron.id = :patronId ")
    Optional<PatronBookBorrow> findByBookPatronId(@Param("bookId") UUID bookId,@Param("patronId") UUID patronId);
}
