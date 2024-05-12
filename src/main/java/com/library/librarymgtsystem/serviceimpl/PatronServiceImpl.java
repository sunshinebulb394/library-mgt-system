package com.library.librarymgtsystem.serviceimpl;

import com.library.librarymgtsystem.model.Book;
import com.library.librarymgtsystem.model.Patron;
import com.library.librarymgtsystem.repository.PatronRepository;
import com.library.librarymgtsystem.service.PatronService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * this class is used implement crud functionality for the patron service
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class PatronServiceImpl implements PatronService {

    private final PatronRepository patronRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     *
     * @param id
     * @return Patron
     */
    @Override
    public Patron findPatronById(UUID id) {
        log.info("inside findPatronById method");
        return patronRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Book Not Found"));

    }

    /**
     *
     * @param patron
     * @return Patron
     */
    @Override
    public Patron addPatron(Patron patron) {
        log.info("inside addBook method");
        Optional<Patron> existingPation = patronRepository.findByEmail(patron.getEmail());
        if (existingPation.isPresent()) {
            throw new EntityExistsException("Patron already exists");
        } else {
            //encode the password before saving
            patron.setPassword(passwordEncoder.encode(patron.getPassword()));
            return patronRepository.save(patron);
        }
    }

    /**
     *
     * @return list of patrons
     */
    @Override
    @Cacheable("patrons")
    public List<Patron> findAllPatrons() {
        log.info("inside findAllPatrons method");

        return patronRepository.findAll();
    }

    /**
     *
     * @param patron
     * @param id
     * @return Patron
     */
    @Override
    @Transactional
    public Patron updatePatron(Patron patron, UUID id) {
        log.info("inside updatePatron method");

        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Patron Not Found"));
       existingPatron.setEmail(patron.getEmail());
       existingPatron.setName(patron.getName());
       existingPatron.setPhoneNumber(patron.getPhoneNumber());
        return patronRepository.save(existingPatron);
    }

    /**
     *
     * @param id
     */
    @Override
    public void deletePatron(UUID id) {
        log.info("inside deletePatron method");

        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Patron Not Found"));
        patronRepository.delete(existingPatron);
    }
}
