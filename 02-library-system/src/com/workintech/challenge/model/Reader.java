package com.workintech.challenge.model;

import com.workintech.challenge.util.Constants;
import com.workintech.challenge.util.EnumTypes.BookStatus;
import java.util.ArrayList;
import java.util.List;

public class Reader extends User {
    private List<Book> borrowedBooks;

    // Constructor
    public Reader(int id, String name, String email, String username, String password, String role, int memberRecordId) {
        super(id, name, email, username, password, role, memberRecordId);
        this.borrowedBooks = new ArrayList<>();
    }


    public void borrowBook(Book book) {
        if (this.borrowedBooks.size() >= Constants.MAX_BOOK_LIMIT) {
            throw new IllegalStateException("Maksimum kitap limitine ulaşıldı.");
        }
        if (book.getStatus() == BookStatus.AVAILABLE) {
            borrowedBooks.add(book);
            book.setCurrentHolder(this);
            book.setStatus(BookStatus.BORROWED);
        } else {
            throw new IllegalStateException("Kitap şu anda mevcut değil ve ödünç alınamaz.");
        }
    }


    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            throw new IllegalStateException("Bu kitap bu okuyucu tarafından ödünç alınmamış.");
        }
        borrowedBooks.remove(book);
        book.setStatus(BookStatus.AVAILABLE);
        book.setCurrentHolder(null);
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks); // Defansif kopya döndür
    }

    @Override
    public String whoYouAre() {
        return String.format("Reader %s, email %s with %d borrowed books", getName(), getEmail(), borrowedBooks.size());
    }
}
