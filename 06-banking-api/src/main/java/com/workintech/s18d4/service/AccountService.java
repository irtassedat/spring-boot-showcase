package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();  // veri tabanında kayıtlı tüm account bilgilerini döner.
    Account find(long id); //İlgili id deki account objesini dönmeli.

    Account save(Account account); // Account objesi ekler

    Account delete(long id); // İlgili id değerindeki account objesini siler.
}