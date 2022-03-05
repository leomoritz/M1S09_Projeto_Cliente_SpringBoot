package com.senai.devinhouse.m1s09_spring_boot.service;

import com.senai.devinhouse.m1s09_spring_boot.controller.dto.AccountDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.dto.AccountOperationDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.dto.AccountTransferDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.forms.NewTransferForm;
import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.account.AccountOperations;
import com.senai.devinhouse.m1s09_spring_boot.model.enums.OperationType;
import com.senai.devinhouse.m1s09_spring_boot.repository.CrudRepository;
import com.senai.devinhouse.m1s09_spring_boot.service.exceptions.AccountOperationNotValidException;
import com.senai.devinhouse.m1s09_spring_boot.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountService implements CrudService<Account> {

    private CrudRepository<Account> repository;

    public AccountService(CrudRepository<Account> repository) {
        this.repository = repository;
    }

    public AccountOperationDTO withdraw(Integer id, Double value) {
        Account account = repository.findById(id).get();
        boolean withdrawIsValid = account.withdraw(value);

        if(withdrawIsValid){
            AccountOperations accountOperations = new AccountOperations(value, account, OperationType.WITHDRAW);
            account.getAccountOperationsList().add(accountOperations);
            return new AccountOperationDTO(accountOperations);
        }

        throw new AccountOperationNotValidException("Withdraw could not be performed!");

    }

    public AccountOperationDTO deposit(Integer id, Double value) {
        Account account = repository.findById(id).get();
        boolean depositIsValid = account.deposit(value);

        if(depositIsValid){
            AccountOperations accountOperations = new AccountOperations(value, account, OperationType.DEPOSIT);
            account.getAccountOperationsList().add(accountOperations);
            return new AccountOperationDTO(accountOperations);
        }

        throw new AccountOperationNotValidException("Deposit could not be performed!");

    }

    public AccountTransferDTO transferValueToOtherAccount(NewTransferForm newTransfer) {

        Account originAccount = repository.findById(newTransfer.getOriginAccountId()).get();
        Account destinyAccount = repository.findById(newTransfer.getDestinyAccountId()).get();

        if (originAccount.transferValueToOtherAccount(destinyAccount, newTransfer.getValue())) {
            AccountOperations accountOperations = new AccountOperations(newTransfer.getValue(), originAccount, OperationType.TRANSFER);
            originAccount.getAccountOperationsList().add(accountOperations);
            return new AccountTransferDTO(accountOperations, destinyAccount);
        }

        throw new AccountOperationNotValidException("Transfer could not be performed!");

    }

    public Set<AccountOperations> getAccountOperations(Integer id){
        Account account = repository.findById(id).get();
        return account.getAccountOperationsList();
    }

    @Override
    public Account create(Account account) {

        if (account == null) {
            throw new NullPointerException("Account cannot be empty (null)");
        }

        repository.create(account);
        return account;
    }

    @Override
    public Set<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> account = repository.findById(id);

        if (account.isEmpty()) {
            throw new EntityNotFoundException("Account with id " + id + " does not exists");
        }
        return account.get();
    }

    @Override
    public boolean update(Integer id, Account account) {

        if (account == null) {
            throw new NullPointerException("Account cannot be empty (null)");
        }

        if (findById(id) != null) {
            return repository.update(id, account);
        }

        return false;
    }

    @Override
    public boolean delete(Integer id) {

        if (findById(id) != null) {
            return repository.delete(id);
        }
        return false;
    }

}
