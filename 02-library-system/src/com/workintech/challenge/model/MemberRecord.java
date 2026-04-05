package com.workintech.challenge.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class MemberRecord implements Holder{
    private int memberId;
    private String memberType;
    private Date dateOfMembership;
    private int noOfBooksIssued;
    private int maxBookLimit;
    private List<Book> currentBooks;

    // Constructor
    public MemberRecord(int memberId, String memberType, Date dateOfMembership, int noOfBooksIssued, int maxBookLimit) {
        this.memberId = memberId;
        this.memberType = memberType;
        this.dateOfMembership = dateOfMembership;
        this.noOfBooksIssued = noOfBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.currentBooks = new ArrayList<>();
    }

    // Getters and Setters
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        if (memberId < 0) {
            throw new IllegalArgumentException("Üye ID negatif olamaz.");
        }
        this.memberId = memberId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        if (memberType == null || memberType.trim().isEmpty()) {
            throw new IllegalArgumentException("Üye tipi boş olamaz.");
        }
        this.memberType = memberType;
    }

    public Date getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(Date dateOfMembership) {
        if (dateOfMembership == null || dateOfMembership.after(new Date())) {
            throw new IllegalArgumentException("Üyelik tarihi bugünden sonra olamaz.");
        }
        this.dateOfMembership = dateOfMembership;
    }

    public int getNoOfBooksIssued() {
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(int noOfBooksIssued) {
        if (noOfBooksIssued < 0) {
            throw new IllegalArgumentException("Çıkarılan kitap sayısı negatif olamaz.");
        }
        this.noOfBooksIssued = noOfBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        if (maxBookLimit <= 0) {
            throw new IllegalArgumentException("Maksimum kitap limiti sıfırdan büyük olmalıdır.");
        }
        this.maxBookLimit = maxBookLimit;
    }

    public List<Book> getCurrentBooks() {
        return currentBooks;
    }

    public void addBook(Book book) {
        if (currentBooks.size() < maxBookLimit) {
            currentBooks.add(book);
            noOfBooksIssued++;
        } else {
            throw new IllegalStateException("Maksimum kitap limitine ulaşılmıştır.");
        }
    }

    public void returnBook(Book book) {
        if (currentBooks.contains(book)) {
            currentBooks.remove(book);
            noOfBooksIssued--;
        } else {
            throw new IllegalStateException("Bu kitap bu üye tarafından ödünç alınmamış.");
        }
    }
    public void incrementBooksIssued() {
        this.noOfBooksIssued++;
    }


    @Override
    public int getId() {
        return memberId;
    }

    @Override
    public String getName() {
        return memberType;  // Burada daha anlamlı bir isim döndürmek isteyebilirsiniz.
    }
}
