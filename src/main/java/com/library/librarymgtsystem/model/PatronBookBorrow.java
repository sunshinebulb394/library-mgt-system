package com.library.librarymgtsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatronBookBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patron_id")
    Patron patron;

    private LocalDateTime borrowedDate;
    private LocalDateTime returnedDate;

}
