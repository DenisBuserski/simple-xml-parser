package com.simplexmlparser.entities;

import com.simplexmlparser.enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "books")
public class Book {
    private String id;
    private String title;
    private Genre genre;
    private BigDecimal price;
    @Column(name = "publish_date")
    private LocalDate publishDate;
    private String description;
}
