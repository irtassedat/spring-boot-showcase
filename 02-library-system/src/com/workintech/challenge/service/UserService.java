package com.workintech.challenge.service;

import com.workintech.challenge.model.Library;
import com.workintech.challenge.model.User;
import java.util.List;
import java.util.Date;
import com.workintech.challenge.util.Constants;
import com.workintech.challenge.model.MemberRecord;


public class UserService {
    private Library library;

    public UserService(Library library) {
        this.library = library;
    }

    public User addUser(String name, String email, String username, String password, String role, String memberType) {
        MemberRecord memberRecord = new MemberRecord(
                library.generateMemberId(),
                memberType,
                new Date(),
                0,
                Constants.MAX_BOOK_LIMIT
        );
        library.addMemberRecord(memberRecord);

        User newUser = new User(
                library.generateUserId(),
                name,
                email,
                username,
                password,
                role,
                memberRecord.getMemberId()
        );
        library.addUser(newUser);
        return newUser;
    }



    public User getUser(int userId) {
        return library.getUser(userId);
    }


    public List<User> listAllUsers() {
        return library.getAllUsers();
    }

    public User authenticateUser(String username, String password) {
        for (User user : library.getAllUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
