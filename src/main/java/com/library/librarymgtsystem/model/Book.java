package com.library.librarymgtsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotNull(message = "author cannot be null")
    @NotEmpty(message = "author cannot be empty")
    private String author;
    @NotNull(message = "publication year cannot be null")
    @NotEmpty(message = "publication year cannot be empty")
    private String publicationYear;

    @NotNull(message = "ISBN cannot be null")
    @NotEmpty(message = "ISBN cannot be empty")
    private String ISBN;

    @OneToMany(mappedBy = "book")
    private List<PatronBookBorrow> patrons;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
