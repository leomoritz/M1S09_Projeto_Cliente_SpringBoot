package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.model.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.Customer;
import com.senai.devinhouse.m1s09_spring_boot.model.enums.AccountType;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    private CrudService<Account> service;
    private CrudService<Customer> customerService;

    public AccountController(CrudService<Account> service, CrudService<Customer> customerService) {
        this.service = service;
        this.customerService = customerService;
    }

    @GetMapping("/getAccountList")
    public Set<Account> getAccountList(){
        return service.findAll();
    }

    @PostMapping("/registerAccount")
    public boolean registerAccount(@RequestBody Account account){
        return service.create(account);
    }

}
