package com.workintech.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String name;
    private String biography;
    private List<Book> books;

    // Constructor
    public Author(int id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }


    public List<Book> getBooks() {
        return books;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", books=" + books.stream().map(Book::getTitle).toList() +
                '}';
    }
}
