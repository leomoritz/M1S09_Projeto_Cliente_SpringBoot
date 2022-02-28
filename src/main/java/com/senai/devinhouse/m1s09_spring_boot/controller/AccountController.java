package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.model.Account;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<Set<Account>> getAccountList() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/getAccountById/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) throws Exception {
       return ResponseEntity.ok().body(service.findById(id).get());
    }

    @PostMapping("registerAccount")
    public ResponseEntity<String> registerAccount(@RequestBody @Valid Account account, UriComponentsBuilder uriBuilder) {
        Account newAccount = service.create(account);
        URI uri = uriBuilder.path("/registerAccount").buildAndExpand(newAccount).toUri();
        return ResponseEntity.created(uri).body("\"id\":" + "\"" + newAccount.getId() + "\"");
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
