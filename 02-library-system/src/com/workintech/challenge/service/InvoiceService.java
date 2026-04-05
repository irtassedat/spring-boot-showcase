package com.workintech.challenge.service;

import com.workintech.challenge.model.Invoice;
import java.util.ArrayList;
import java.util.List;
import com.workintech.challenge.model.MemberRecord;
import com.workintech.challenge.model.Book;
import java.util.stream.Collectors;


public class InvoiceService {
    private List<Invoice> invoices = new ArrayList<>();

    // Fatura oluşturma
    public void createInvoice(MemberRecord member, Book book, String transactionType) {
        double amount = calculateAmount(book, transactionType);
        String description = "Transaction: " + transactionType + " - Book: " + book.getTitle();
        // Gerçek bir fatura oluşturma işlemi burada gerçekleşir
        System.out.println("Invoice created for " + member.getMemberType() + " - " + description + " - Amount: $" + amount);
    }



    public Invoice getInvoice(int invoiceId) {
        for (Invoice invoice : invoices) {
            if (invoice.getId() == invoiceId) {
                return invoice;
            }
        }
        return null;
    }

    public List<Invoice> listInvoicesForUser(int userId) {
        return invoices.stream()
                .filter(invoice -> invoice.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public List<Invoice> listAllInvoices() {
        return new ArrayList<>(invoices);
    }


    public double calculateAmount(Book book, String transactionType) {
        double basePrice = book.getPrice();
        if (transactionType.equals("borrow")) {
            return basePrice * 0.1;  // Ödünç alma işlemi için %10 ücret alınsın
        } else if (transactionType.equals("return")) {
            return basePrice * 0.05;  // İade işlemi için %5 iade edilsin
        }
        return 0;
    }
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }


}
