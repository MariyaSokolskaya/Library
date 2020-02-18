package com.samsung.itschool.library;

import java.util.Comparator;

public class Book {
    String author = "";
    String title = "";
    int year;
    String genre;

    public Book(String author, String title, int year , String genre) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Author: " + author + "\nTitle: " + title + "\nYear: "
                + year + "\nGenre: " + genre;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
class CompareBook implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {

        return b1.author.compareTo(b2.author);
    }
}

