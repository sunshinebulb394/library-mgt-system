package com.library.librarymgtsystem.serviceimpl;

import com.library.librarymgtsystem.model.PatronBookBorrow;
import com.library.librarymgtsystem.repository.BookRepository;
import com.library.librarymgtsystem.repository.PatronBookBorrowRepository;
import com.library.librarymgtsystem.repository.PatronRepository;
import com.library.librarymgtsystem.service.PatronBookBorrowService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * this class is used implement crud functionality for the borrowing of books by patrons
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class PatronPatronBookBorrowServiceImpl implements PatronBookBorrowService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final PatronBookBorrowRepository patronBookBorrowRepository;


    @Override
    @Transactional
    public String borrowBook(UUID bookId, UUID patronId) {
        log.info("in borrowBook method");
        //check if book exists
      var book =  bookRepository.findById(bookId).orElseThrow(()-> new EntityNotFoundException("Book Not Found"));
        //check if patron exist
      var patron =   patronRepository.findById(patronId).orElseThrow(()-> new EntityNotFoundException("Patron Not Found"));

        PatronBookBorrow patronBookBorrow = new PatronBookBorrow();
        patronBookBorrow.setBook(book);
        patronBookBorrow.setPatron(patron);
        //initialize the time the borrow request was sent
        patronBookBorrow.setBorrowedDate(LocalDateTime.now());
        patronBookBorrowRepository.save(patronBookBorrow);
        return "Book borrowed Successfully";
    }

    /**
     * fetch the borrowed record by the borrowd date ,book id and patronId
     * @param bookId
     * @param patronId
     * @return
     */
    @Override
    @Transactional
    public String returnBook(UUID bookId, UUID patronId) {
        log.info("Inside returnBook method");
        //check if book exists
        bookRepository.findById(bookId).orElseThrow(()-> new EntityNotFoundException("Book Not Found"));
        //check if patron exist
         patronRepository.findById(patronId).orElseThrow(()-> new EntityNotFoundException("Patron Not Found"));

       var borrowRecord =  patronBookBorrowRepository.findByBookPatronId(bookId,patronId).orElseThrow(()-> new EntityNotFoundException("Borrowed record not found"));

       borrowRecord.setReturnedDate(LocalDateTime.now());
       patronBookBorrowRepository.save(borrowRecord);

       return "Book Returned successfully";
    }
}
