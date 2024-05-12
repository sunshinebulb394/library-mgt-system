package com.library.librarymgtsystem.controller;

import com.library.librarymgtsystem.model.Book;
import com.library.librarymgtsystem.model.Patron;
import com.library.librarymgtsystem.service.PatronService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/patron")
public class PatronController {

    private final PatronService patronService;
    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron) {
        log.info("inside POST api -> /api/patron");
        return ResponseEntity.status(HttpStatus.CREATED).body(patronService.addPatron(patron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@RequestBody Patron patron,@PathVariable UUID id) {
        log.info("inside PUT api -> /api/patron/{id}");

        return ResponseEntity.ok(patronService.updatePatron(patron,id));
    }

    @GetMapping({"/{id}",""})
    public ResponseEntity<?> getPatrons(@PathVariable(required = false) UUID id) {
        log.info("inside GET api -> /api/patron/{id}");

        if (id != null) {
            return ResponseEntity.ok(patronService.findPatronById(id));
        }
        return ResponseEntity.ok(patronService.findAllPatrons());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooks(@PathVariable UUID id) {
        log.info("inside DELETE api -> /api/patron/{id}");

        patronService.deletePatron(id);
        return ResponseEntity.ok("Patron deleted successfully");

    }


}
