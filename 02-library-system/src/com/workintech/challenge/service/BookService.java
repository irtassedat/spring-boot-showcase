package com.workintech.challenge.service;

import com.workintech.challenge.model.Book;
import com.workintech.challenge.model.Library;
import com.workintech.challenge.util.EnumTypes.BookStatus;

public class BookService {
    private Library library;

    public BookService(Library library) {
        this.library = library;
    }


    public void addBook(Book book) {;
        library.addBook(book);
    }


    public Book getBook(int bookId) {
        return library.getBook(bookId);
    }


    public void updateBook(int bookId, String newTitle, String newAuthor, double newPrice, String newEdition, BookStatus newStatus) {
        Book book = getBook(bookId);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setPrice(newPrice);
            book.setEdition(newEdition);
            book.setStatus(newStatus);
        }
    }


    public void updateBookStatus(int bookId, BookStatus status) {
        Book book = getBook(bookId);
        if (book != null) {
            book.setStatus(status);
        }
    }

    // Kitap silme
    public void removeBook(int bookId) {
        library.removeBook(bookId);
    }

    public void updatePurchaseDate(int bookId, java.util.Date newPurchaseDate) {
        Book book = getBook(bookId);
        if (book != null) {
            book.setPurchaseDate(newPurchaseDate);
        }
    }
}
