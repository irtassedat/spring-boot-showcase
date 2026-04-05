package com.workintech.challenge.model;

import com.workintech.challenge.util.EnumTypes.BookStatus;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String edition;
    private Date purchaseDate;
    private BookStatus status;
    private Date borrowedDate;
    private Date returnedDate;
    private Holder currentHolder;
    private String category;

    // Constructor
    public Book(int id, String title, String author, double price, String edition, Date purchaseDate, BookStatus status,String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.setPrice(price);
        this.edition = edition;
        this.setPurchaseDate(purchaseDate);
        this.status = status;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Fiyat negatif olamaz.");
        }
        this.price = price;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        if (purchaseDate == null || purchaseDate.after(new Date())) {
            throw new IllegalArgumentException("Satın alma tarihi bugünden sonra olamaz.");
        }
        this.purchaseDate = purchaseDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
        // Kitabın durumuna bağlı olarak ek işlemler
        if (status == BookStatus.BORROWED) {
            this.borrowedDate = new Date();
        } else if (status == BookStatus.AVAILABLE) {
            this.returnedDate = new Date();
            this.currentHolder = null;
        }
    }

    public Holder getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(Holder currentHolder) {
        this.currentHolder = currentHolder;
    }

    // toString metodu
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", edition='" + edition + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", status=" + status +  // Enum tipini doğru bir şekilde yazdırır
                ", borrowedDate=" + borrowedDate +
                ", returnedDate=" + returnedDate +
                ", currentHolder=" + (currentHolder != null ? currentHolder.getName() : "none") +  // Person nesnesinin ismini yazdırır
                '}';
    }
}
