package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.model.Account;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/account")
public class AccountController {

    private CrudService<Account> service;

    public AccountController(CrudService<Account> service) {
        this.service = service;
    }

    @GetMapping("/getAccountList")
    public Set<Account> getAccountList(){
        return service.findAll();
    }

    @GetMapping("/getAccountById/{id}")
    public Account getAccountById(@PathVariable Integer id) throws Exception {
        if(service.findById(id).isPresent()){
            return service.findById(id).get();
        }
        throw new Exception("Account not found");
    }


    @PostMapping("/registerAccount")
    public boolean registerAccount(@RequestBody Account account){
        return service.create(account);
    }

    @PutMapping("/updateAccountById/{id}")
    public boolean updateAccountById(@PathVariable Integer id, @RequestBody Account account){
        return service.update(id, account);
    }

    @DeleteMapping("/deleteAccountById/{id}")
    public boolean deleteAccountById(@PathVariable Integer id){
        return service.delete(id);
    }

}
