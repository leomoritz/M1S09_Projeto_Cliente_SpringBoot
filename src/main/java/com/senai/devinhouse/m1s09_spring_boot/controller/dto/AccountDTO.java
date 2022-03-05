package com.senai.devinhouse.m1s09_spring_boot.controller.dto;

import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.account.AccountOperations;
import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import com.senai.devinhouse.m1s09_spring_boot.model.enums.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private Integer id;
    private Customer customer;
    private AccountType accountType;
    private Double balance;

    public AccountDTO(Account account){
        this.id = account.getId();
        this.customer = account.getCustomer();
        this.accountType = account.getAccountType();
        this.balance = account.getBalance();
    }

}
