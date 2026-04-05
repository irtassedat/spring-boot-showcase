package com.workintech.challenge.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();
    private List<MemberRecord> members;
    private Map<Integer, Reader> readers = new HashMap<>();
    private int nextUserId = 1;
    private int nextMemberId = 1;

    // Constructor
    public Library() {
        this.members = new ArrayList<>();
    }


    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            throw new IllegalStateException("Bu ID'ye sahip bir kitap zaten mevcut.");
        }
        books.put(book.getId(), book);
    }


    public void removeBook(int bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
        } else {
            throw new IllegalStateException("Bu ID'ye sahip kitap mevcut değil.");
        }
    }


    public void addMember(MemberRecord member) {
        members.add(member);
    }


    public Book getBook(int bookId) {
        return books.get(bookId);
    }


    public void addReader(Reader reader) {
        readers.put(reader.getId(), reader);
    }


    public Reader getReader(int readerId) {
        return readers.get(readerId);
    }


    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public List<MemberRecord> getMembers() {
        return members;
    }


    public void addUser(User user) {
        user.setId(nextUserId);
        users.put(user.getId(), user);
        nextUserId++;
    }



    public User getUser(int userId) {
        return users.get(userId);
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Reader> getReaders() {
        return new ArrayList<>(readers.values());
    }

    public MemberRecord getMember(int memberId) {
        for (MemberRecord member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void addMemberRecord(MemberRecord member) {
        member.setMemberId(nextMemberId);
        members.add(member);
        nextMemberId++;
    }

    public int generateMemberId() {
        return nextMemberId;
    }

    public int generateUserId() {
        return nextUserId;
    }
}
