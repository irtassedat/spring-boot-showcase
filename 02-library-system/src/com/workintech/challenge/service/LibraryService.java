package com.workintech.challenge.service;

import com.workintech.challenge.model.Book;
import com.workintech.challenge.model.Library;
import com.workintech.challenge.model.User;
import com.workintech.challenge.model.MemberRecord;
import com.workintech.challenge.util.EnumTypes.BookStatus;
import com.workintech.challenge.model.TransactionDetails;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;



public class LibraryService {
    private Library library;
    private UserService userService;
    private BookService bookService;
    private InvoiceService invoiceService;

    public LibraryService(Library library, UserService userService, BookService bookService, InvoiceService invoiceService) {
        this.library = library;
        this.userService = userService;
        this.bookService = bookService;
        this.invoiceService = invoiceService;
    }

    // Kitap işlemleri
    public void addBook(Book book) {
        library.addBook(book);
    }

    public Book getBook(int bookId) {
        return library.getBook(bookId);
    }

    public void removeBook(int bookId) {
        library.removeBook(bookId);
    }

    public void borrowBook(int bookId, int memberId) {
        Book book = getBook(bookId);
        MemberRecord member = getMember(memberId);
        if (book != null && member != null && book.getStatus() == BookStatus.AVAILABLE && member.getNoOfBooksIssued() < member.getMaxBookLimit()) {
            book.setCurrentHolder(member); // Cast işlemi veya metod güncellemesi gerekebilir
            book.setStatus(BookStatus.BORROWED);
            member.addBook(book);
            // Fatura oluşturma
            TransactionDetails details = new TransactionDetails("Ödünç alındı: " + book.getTitle());
            invoiceService.createInvoice(member, book, "borrow");
            System.out.println("Book borrowed successfully. Invoice created.");
        } else {
            System.out.println("Book is not available or member has reached the book limit.");
        }
    }



    public void returnBook(int bookId, int memberId) {
        Book book = getBook(bookId);
        MemberRecord member = getMember(memberId);
        if (book != null && member != null && book.getCurrentHolder().equals(member)) {
            book.setCurrentHolder(null);
            book.setStatus(BookStatus.AVAILABLE);
            member.returnBook(book);
            // İade faturası oluşturma
            TransactionDetails details = new TransactionDetails("İade edildi: " + book.getTitle());
            invoiceService.createInvoice(member, book, "return");
            System.out.println("Book returned successfully. Refund invoice created.");
        } else {
            System.out.println("This book was not borrowed by this member.");
        }
    }

    public Book getBookById(int id) {
        return library.getBooks().stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Book> getBooksByTitle(String title) {
        return library.getBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(String author) {
        return library.getBooks().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByCategory(String category) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }
    public List<Book> listAllBooks() {
        return library.getBooks();
    }

    // Üye işlemleri
    public void addMember(MemberRecord member) {
        library.addMember(member);
    }

    public MemberRecord getMember(int memberId) {
        return library.getMember(memberId);
    }

    public List<MemberRecord> listAllMembers() {
        return library.getMembers();
    }

    public void updateBookWithAuthorization(int userId, int bookId, String newTitle, String newAuthor, double newPrice, String newEdition, BookStatus newStatus) {
        User user = userService.getUser(userId);
        if (user != null && user.getRole().equals("LIBRARIAN")) {
            bookService.updateBook(bookId, newTitle, newAuthor, newPrice, newEdition, newStatus);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Unauthorized attempt to update book. Only librarians can update books.");
        }
    }
}
