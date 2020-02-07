package com.samsung.itschool.library;

import java.util.Comparator;

public class Book {
    String author = "";
    String title = "";
    int year;

    public Book(String author, String title, int year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Author: " + author + "\nTitle: " + title + "\nYear: "
                + year;
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

