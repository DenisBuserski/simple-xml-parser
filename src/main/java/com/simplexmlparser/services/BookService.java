package com.simplexmlparser.services;

import com.simplexmlparser.entities.Book;
import com.simplexmlparser.enums.Genre;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BookService {
    Book addBook(String id, String title, Genre genre, BigDecimal price, LocalDate publishDate, String description)
}
