package com.workintech.challenge.model;

import java.util.Date;

public class Invoice {
    private int id;
    private int userId;
    private double amount;
    private Date issueDate;
    private TransactionDetails details;

    // Constructor
    public Invoice(int id, int userId, double amount, Date issueDate, TransactionDetails details) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.issueDate = issueDate;
        this.details = details;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public TransactionDetails getDetails() {
        return details;
    }

    public void setDetails(TransactionDetails details) {
        this.details = details;
    }
}
