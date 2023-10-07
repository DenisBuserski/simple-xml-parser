package com.simplexmlparser.services;

import com.simplexmlparser.entities.Book;
import com.simplexmlparser.enums.Genre;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book addBook(String id, String title, Genre genre, BigDecimal price, LocalDate publishDate, String description) {
        return null;
    }
}
