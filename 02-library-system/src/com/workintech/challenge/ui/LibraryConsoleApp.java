package com.workintech.challenge.ui;

import com.workintech.challenge.service.BookService;
import com.workintech.challenge.service.InvoiceService;
import com.workintech.challenge.service.LibraryService;
import com.workintech.challenge.service.UserService;
import com.workintech.challenge.model.Library;
import com.workintech.challenge.model.Book;
import com.workintech.challenge.model.User;
import com.workintech.challenge.util.EnumTypes.BookStatus;
import com.workintech.challenge.model.MemberRecord;
import com.workintech.challenge.model.Invoice;
import com.workintech.challenge.model.TransactionDetails;

import java.text.ParseException;
import java.util.Scanner;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LibraryConsoleApp {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static UserService userService = new UserService(library);
    private static BookService bookService = new BookService(library);
    private static InvoiceService invoiceService = new InvoiceService();
    private static LibraryService libraryService = new LibraryService(library, userService, bookService, invoiceService);


    static {
        initialize();
    }
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to the Library Management System!");
            login();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User loggedInUser = userService.authenticateUser(username, password);
        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome, " + loggedInUser.getUsername() + ".");
            runMainMenu(loggedInUser);
        } else {
            System.out.println("Login failed! Please check your credentials.");
            System.exit(0);
        }
    }
    private static int nextInvoiceId = 1;
    private static int generateInvoiceId() {
        return nextInvoiceId++;
    }
    private static void runMainMenu(User user) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. List All Books");
            if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN")) {
                System.out.println("2. Add Book");
                System.out.println("3. Update Book");
                System.out.println("4. Remove Book");
                System.out.println("10. List Transactions and Invoices");
            }
            if (user.getRole().equals("ADMIN")) {
                System.out.println("5. Add User");
                System.out.println("6. List All Users");
            }
            if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN") || user.getRole().equals("READER")) {
                System.out.println("8. Borrow Book");
                System.out.println("9. Return Book");
            }
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN")) {
                        addBook();
                    }
                    break;
                case 3:
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN")) {
                        updateBook();
                    }
                    break;
                case 4:
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN")) {
                        removeBook();
                    }
                    break;
                case 5:
                    if (user.getRole().equals("ADMIN")) {
                        addUser();
                    }
                    break;
                case 6:
                    if (user.getRole().equals("ADMIN")) {
                        listAllUsers();
                    }
                    break;
                case 8:
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN") || user.getRole().equals("READER")) {
                        borrowBook();
                    }
                    break;
                case 9:
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN") || user.getRole().equals("READER")) {
                        returnBook();
                    }
                    break;
                case 10:
                    if (user.getRole().equals("ADMIN") || user.getRole().equals("LIBRARIAN")) {
                        listTransactionsAndInvoices();
                    }
                case 7:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initialize() {
        userService.addUser("Admin User", "admin@library.com", "admin", "admin", "ADMIN", "Admin");
        userService.addUser("Librarian User", "librarian@library.com", "librarian", "librarian123", "LIBRARIAN", "Staff");
        userService.addUser("Reader User", "reader@library.com", "reader", "reader123", "READER", "Reader");
        try {
            bookService.addBook(new Book(1, "Aşk ve Gurur", "Jane Austen", 150.0, "2", new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"), BookStatus.AVAILABLE, "Klasik"));
            bookService.addBook(new Book(2, "Don Kişot", "Cervantes Saavedra", 200.0, "6", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), BookStatus.AVAILABLE,"Roman"));
            bookService.addBook(new Book(3, "Fareler ve İnsanlar", "John Steinbeck", 320.0, "7", new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"), BookStatus.AVAILABLE,"Roman"));
            bookService.addBook(new Book(4, "Hamlet", "William Shakespeare", 230.0, "10", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), BookStatus.AVAILABLE,"Roman"));
            bookService.addBook(new Book(5, "İki Şehrin Hikayesi", "Charles Dickens", 120.0, "3", new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"), BookStatus.AVAILABLE,"Klasik"));
            bookService.addBook(new Book(6, "Kırmızı ve Siyah", "Henri Beyle Stendhal", 220.0, "11", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), BookStatus.AVAILABLE,"Hikaye"));
            bookService.addBook(new Book(7, "Mutlu Prens", "Oscar Wilde", 150.0, "41", new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"), BookStatus.AVAILABLE,"Hikaye"));
            bookService.addBook(new Book(8, "Savaş ve Barış", "Lev Nikolayeviç Tolstoy", 280.0, "37", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), BookStatus.AVAILABLE,"Roman"));
            bookService.addBook(new Book(9, "Sefiller", "Victor Hugo", 300.0, "44", new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01"), BookStatus.AVAILABLE,"Klasik"));
            bookService.addBook(new Book(10, "Suç ve Ceza", "Dostoyevski", 1000.0, "1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), BookStatus.AVAILABLE,"Roman"));
            bookService.addBook(new Book(11, "TestYazar1", "Dostoyevski", 1000.0, "1", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-01"), BookStatus.AVAILABLE,"Roman"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addBook() {
        System.out.print("Enter your user ID for authorization: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("User not found. Access denied.");
            return;
        }

        if (!(user.getRole().equals("LIBRARIAN") || user.getRole().equals("ADMIN"))) {
            System.out.println("Access Denied: Only librarians and admins can add books.");
            return;
        }

        try {
            System.out.print("Enter book ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter author: ");
            String author = scanner.nextLine();
            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter edition: ");
            String edition = scanner.nextLine();
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter status (AVAILABLE, BORROWED, RESERVED, MAINTENANCE): ");
            String statusInput = scanner.nextLine();
            BookStatus status = BookStatus.valueOf(statusInput.toUpperCase());

            System.out.print("Enter purchase date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            Date purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);

            Book newBook = new Book(id, title, author, price, edition, purchaseDate, status, category);
            bookService.addBook(newBook);
            System.out.println("Book added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again.");
        }
    }

    private static void listAllBooks() {
        System.out.println("\n--- Book Listing Menu ---");
        System.out.println("1. List all books");
        System.out.println("2. Search books by ID");
        System.out.println("3. Search books by title");
        System.out.println("4. Search books by author");
        System.out.println("5. List books by category");
        System.out.println("6. Return to main menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                printBooksDetailed(libraryService.listAllBooks());
                break;
            case 2:
                System.out.print("Enter book ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                printBookDetailed(libraryService.getBookById(id));
                break;
            case 3:
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                printBooksDetailed(libraryService.getBooksByTitle(title));
                break;
            case 4:
                System.out.print("Enter author's name: ");
                String author = scanner.nextLine();
                printBooksDetailed(libraryService.getBooksByAuthor(author));
                break;
            case 5:
                System.out.print("Enter category name: ");
                String category = scanner.nextLine();
                List<Book> booksInCategory = libraryService.getBooksByCategory(category);
                printBooksDetailed(booksInCategory);
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void printBooksDetailed(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                String holderDetails = book.getCurrentHolder() == null ? "Available" : "Borrowed by " + book.getCurrentHolder().getName() + " (ID: " + book.getCurrentHolder().getId() + ")";
                System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor() + " - " + holderDetails);
            }
        }
    }

    private static void printBookDetailed(Book book) {
        if (book == null) {
            System.out.println("No book found with the given ID.");
        } else {
            String holderDetails = book.getCurrentHolder() == null ? "Available" : "Borrowed by " + book.getCurrentHolder().getName() + " (ID: " + book.getCurrentHolder().getId() + ")";
            System.out.println("Book ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Status: " + holderDetails);
        }
    }


    private static void printBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book book : books) {
                System.out.println(book.getId() + " - " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    private static void printBook(Book book) {
        if (book == null) {
            System.out.println("No book found with the given ID.");
        } else {
            System.out.println("Book ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
        }
    }


    private static void borrowBook() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        MemberRecord member = libraryService.getMember(user.getMemberRecordId());
        if (member == null) {
            System.out.println("No member record found for ID: " + user.getMemberRecordId());
            return;
        }

        Book book = bookService.getBook(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.getStatus() == BookStatus.AVAILABLE && member.getNoOfBooksIssued() < member.getMaxBookLimit()) {
            book.setStatus(BookStatus.BORROWED);
            book.setCurrentHolder(user);
            member.incrementBooksIssued();
            user.getBorrowedBooks().add(book);

            double amount = invoiceService.calculateAmount(book, "borrow");
            String description = "Borrowed book: " + book.getTitle();
            Invoice invoice = new Invoice(generateInvoiceId(), userId, amount, new Date(), new TransactionDetails(description));
            invoiceService.addInvoice(invoice);

            System.out.println("Book borrowed successfully. Invoice ID: " + invoice.getId());
        } else {
            System.out.println("Book is not available or member has reached the book limit.");
        }

    }





    private static void returnBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        MemberRecord member = libraryService.getMember(user.getMemberRecordId());
        try {
            libraryService.returnBook(bookId, member.getMemberId());
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }


    private static void addUser() {
        System.out.print("Enter your user ID for authorization: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("User not found. Access denied.");
            return;
        }

        if (!user.getRole().equals("ADMIN")) {
            System.out.println("Access Denied: Only admins can add users.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();
        System.out.print("Enter member type (e.g., Student, Faculty): ");
        String memberType = scanner.nextLine();

        User newUser = userService.addUser(name, email, username, password, role, memberType);
        if (newUser != null) {
            System.out.println("User added successfully. User ID: " + newUser.getId());
        } else {
            System.out.println("Failed to add user.");
        }
    }



    private static void listAllUsers() {
        List<User> users = userService.listAllUsers();
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found.");
        }
    }

    private static void updateBook() {
        System.out.print("Enter your user ID for authorization: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.getUser(userId);
        if (user == null) {
            System.out.println("User not found. Access denied.");
            return;
        }

        if (!(user.getRole().equals("LIBRARIAN") || user.getRole().equals("ADMIN"))) {
            System.out.println("Access Denied: Only librarians and admins can update books.");
            return;
        }

        List<Book> books = libraryService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available to update. Please add some books first.");
            return;
        }

        System.out.println("Available books:");
        for (Book book : books) {
            System.out.println(book.getId() + " - " + book.getTitle());
        }

        System.out.print("Enter the book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        Book book = bookService.getBook(bookId);
        if (book == null) {
            System.out.println("No book found with the given ID.");
            return;
        }

        System.out.print("Enter new title (leave blank to not change): ");
        String title = scanner.nextLine();
        System.out.print("Enter new author (leave blank to not change): ");
        String author = scanner.nextLine();
        System.out.print("Enter new price (0 to not change): ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // newline karakterini temizle
        System.out.print("Enter new edition (leave blank to not change): ");
        String edition = scanner.nextLine();
        System.out.print("Enter new status (AVAILABLE, BORROWED, RESERVED, MAINTENANCE, leave blank to not change): ");
        String statusInput = scanner.nextLine();

        BookStatus status = null;
        if (!statusInput.isEmpty()) {
            status = BookStatus.valueOf(statusInput.toUpperCase());
        }

        bookService.updateBook(bookId, title, author, price, edition, status);
        System.out.println("Book updated successfully.");
    }

    private static void removeBook() {
        System.out.println("Available books:");
        listAllBooks();

        System.out.print("Enter the book ID to remove: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            bookService.removeBook(bookId);
            System.out.println("Book removed successfully.");
        } catch (Exception e) {
            System.out.println("Error removing book: " + e.getMessage());
        }
    }

    private static void listTransactionsAndInvoices() {
        System.out.println("\n--- Transaction and Invoice Menu ---");
        System.out.println("1. List all invoices");
        System.out.println("2. List invoices for a specific user");
        System.out.println("3. Return to main menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                printAllInvoices();
                break;
            case 2:
                System.out.print("Enter User ID: ");
                int userId = scanner.nextInt();
                scanner.nextLine();
                printInvoicesForUser(userId);
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void printAllInvoices() {
        List<Invoice> allInvoices = invoiceService.listAllInvoices();
        if (allInvoices.isEmpty()) {
            System.out.println("No invoices found.");
        } else {
            for (Invoice invoice : allInvoices) {
                System.out.println("Invoice ID: " + invoice.getId() + ", User ID: " + invoice.getUserId() + ", Amount: $" + invoice.getAmount() + ", Date: " + invoice.getIssueDate() + ", Description: " + invoice.getDetails().getDescription());
            }
        }
    }

    private static void printInvoicesForUser(int userId) {
        List<Invoice> userInvoices = invoiceService.listInvoicesForUser(userId);
        if (userInvoices.isEmpty()) {
            System.out.println("No invoices found for User ID: " + userId);
        } else {
            for (Invoice invoice : userInvoices) {
                System.out.println("Invoice ID: " + invoice.getId() + ", Amount: $" + invoice.getAmount() + ", Date: " + invoice.getIssueDate() + ", Description: " + invoice.getDetails().getDescription());
            }
        }
    }



}