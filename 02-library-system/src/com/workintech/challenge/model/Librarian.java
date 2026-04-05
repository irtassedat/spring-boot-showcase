package com.workintech.challenge.model;

import com.workintech.challenge.util.EnumTypes.UserRole;
import com.workintech.challenge.util.EnumTypes.BookStatus;

public class Librarian extends Person {
    private UserRole role;

    // Constructor
    public Librarian(int id, String name, String email, UserRole role) {
        super(id, name, email);
        this.role = role;
    }


    public void lendBook(Book book, Reader reader) {
        if (book.getStatus() == BookStatus.AVAILABLE) {
            reader.borrowBook(book);
            book.setStatus(BookStatus.BORROWED);
        } else {
            throw new IllegalStateException("Bu kitap ödünç verilemez durumda.");
        }
    }


    public void receiveReturnedBook(Book book) {
        if (book.getStatus() == BookStatus.BORROWED) {
            book.setStatus(BookStatus.AVAILABLE);
            book.setCurrentHolder(null);
        } else {
            throw new IllegalStateException("Bu kitap iade edilemez durumda.");
        }
    }


    @Override
    public String whoYouAre() {
        return "Librarian: " + getName() + ", Email: " + getEmail() + ", Role: " + role;
    }

    // Getters and Setters for role
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
