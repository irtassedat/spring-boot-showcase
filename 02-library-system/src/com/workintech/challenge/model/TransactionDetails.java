package com.workintech.challenge.model;

public class TransactionDetails {
    private String description;

    public TransactionDetails(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
