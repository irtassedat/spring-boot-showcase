package com.workintech.challenge.model;

import com.workintech.challenge.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private int memberRecordId;
    private List<Book> borrowedBooks;
    private String username;
    private String password;
    private String role;


    public User(int id, String name, String email, String username, String password, String role, int memberRecordId) {
        super(id, name, email);
        this.username = username;
        this.password = password;
        this.role = role;
        this.memberRecordId = memberRecordId;
        this.borrowedBooks = new ArrayList<>();
    }



    // User özgü Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Kullanıcı adı boş olamaz.");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Parola en az 6 karakter uzunluğunda olmalıdır.");
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Rol boş olamaz.");
        }
        this.role = role;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void addBook(Book book) {
        if (borrowedBooks.size() < Constants.MAX_BOOK_LIMIT) {
            borrowedBooks.add(book);
        } else {
            System.out.println("Cannot borrow more books. Limit reached.");
        }
    }

    public int getMemberRecordId() {
        return memberRecordId;
    }

    public void setMemberRecordId(int memberRecordId) {
        this.memberRecordId = memberRecordId;
    }
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String whoYouAre() {
        return String.format("User %s with role %s", getName(), getRole());
    }

    @Override
    public String toString() {
        String userInfo = "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", borrowedBooks=" + borrowedBooks.size();
        if (!borrowedBooks.isEmpty()) {
            userInfo += ", Borrowed Books Details:";
            for (Book book : borrowedBooks) {
                userInfo += String.format("\n\t%d - %s by %s", book.getId(), book.getTitle(), book.getAuthor());
            }
        }
        userInfo += '}';
        return userInfo;
    }
}
