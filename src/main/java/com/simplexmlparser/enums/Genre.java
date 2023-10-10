package com.simplexmlparser.enums;

public enum Genre {
    COMPUTER("Computer"),
    FANTASY("Fantasy"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    SCIENCE_FICTION("Science Fiction");

    private final String value;

    Genre(String value) {
        this.value = value;
    }
}
