package com.senai.devinhouse.m1s09_spring_boot.service;

import com.senai.devinhouse.m1s09_spring_boot.model.Account;
import com.senai.devinhouse.m1s09_spring_boot.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AccountService implements CrudService<Account> {

    private CrudRepository<Account> repository;

    public AccountService(CrudRepository<Account> repository) {
        this.repository = repository;
    }

    @Override
    public Account create(Account account) {
        if(repository.create(account)){
            return account;
        }
        return null;
    }

    @Override
    public Set<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(Integer id, Account account) {
        return repository.update(id, account);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

}
