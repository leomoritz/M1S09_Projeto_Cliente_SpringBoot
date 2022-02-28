package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.senai.devinhouse.m1s09_spring_boot.model.Account;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.UnknownServiceException;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/account")
public class AccountController {

    private CrudService<Account> service;

    public AccountController(CrudService<Account> service) {
        this.service = service;
    }

    /*CRUD*/
    @GetMapping("/getAccountList")
    public Set<Account> getAccountList() {
        return service.findAll();
    }

    @GetMapping("/getAccountById/{id}")
    public Account getAccountById(@PathVariable Integer id) throws Exception {
        if (service.findById(id).isPresent()) {
            return service.findById(id).get();
        }
        throw new Exception("Account not found");
    }

    @PostMapping("/registerAccount")
    public boolean registerAccount(@RequestBody Account account) {
        return service.create(account);
    }

    @PutMapping("/updateAccountById/{id}")
    public boolean updateAccountById(@PathVariable Integer id, @RequestBody Account account) {
        return service.update(id, account);
    }

    @DeleteMapping("/deleteAccountById/{id}")
    public boolean deleteAccountById(@PathVariable Integer id) {
        return service.delete(id);
    }

    /*OTHER METHODS*/
    @PutMapping("/withdraw/{id}")
    public boolean withdraw(@PathVariable Integer id, @RequestParam Double value) {
        Account account = service.findById(id).get();
        return account.withdraw(value);
    }

    @PutMapping(value = "/deposit/{id}")
    public boolean deposit(@PathVariable Integer id, @RequestParam Double value) {
        Account account = this.service.findById(id).get();
        return account.deposit(value);
    }

    @PutMapping("/transferValueToOtherAccount")
    public Set<Account> transferValueToOtherAccount(@RequestParam int idOriginAccount, int idDestinyAccount,
                                                    double value) throws Exception {

        Account originAccount = service.findById(idOriginAccount).get();
        Account destinyAccount = service.findById(idDestinyAccount).get();

        if (originAccount.transferValueToOtherAccount(destinyAccount, value)) {
            Set<Account> accountList = new TreeSet<>();
            accountList.add(originAccount);
            accountList.add(destinyAccount);
            return accountList;
        }
        return null;
    }

}
