package com.senai.devinhouse.m1s09_spring_boot.repository;

import com.senai.devinhouse.m1s09_spring_boot.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class AccountRepository implements CrudRepository<Account> {

    private static final Set<Account> REPOSITORY = new TreeSet<>();

    @Override
    public boolean create(Account account) {
        return REPOSITORY.add(account);
    }

    @Override
    public Set<Account> findAll() {
        return REPOSITORY;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return REPOSITORY.stream().filter(account -> account.getId().equals(id)).findFirst();
    }

    @Override
    public boolean update(Integer id, Account account) {
        if (findById(id).isEmpty()) {
            return false;
        }

        // Lógica para sobrescrever na lista o mesmo identificador com as novas informações atualizadas
        delete(id);
        account.setId(id);
        create(account);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        if (findById(id).isEmpty()) {
            return false;
        }
        return REPOSITORY.remove(findById(id).get());
    }
}
