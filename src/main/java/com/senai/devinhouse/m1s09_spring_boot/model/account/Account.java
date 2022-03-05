package com.senai.devinhouse.m1s09_spring_boot.model.account;

import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import com.senai.devinhouse.m1s09_spring_boot.model.enums.AccountType;
import com.senai.devinhouse.m1s09_spring_boot.utils.UtilGeradorId;
import lombok.*;

import java.util.*;

@Getter
@Setter
public class Account implements Comparable<Account> {

    private Integer id = UtilGeradorId.generateSequenceIdAccount();

    private Customer customer;

    private AccountType accountType;

    private final Set<AccountOperations> accountOperationsList;

    private Double balance = 0.0;

    public Account(Customer customer, AccountType accountType) {
        this.customer = customer;
        this.accountType = accountType;
        this.accountOperationsList = new HashSet<>();
    }

    public boolean withdraw(Double value) {
        if (value > 0.0 && value <= this.balance) {
            this.balance -= value;
            return true;
        }
        return false;
    }

    public boolean deposit(Double value) {
        if (value > 0.0) {
            this.balance += value;
            return true;
        }
        return false;
    }

    public boolean transferValueToOtherAccount(Account destinyAccount, Double value) {
        if (!withdraw(value)) {
            return false;
        }
        return destinyAccount.deposit(value);
    }

    @Override
    public String toString() {
        return "Conta: " + id + " | " + "Cliente: " + customer.getName() + " | " + "Tipo Conta: " + accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Account o) {
        return this.id.compareTo(o.id);
    }
}
