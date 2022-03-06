package com.senai.devinhouse.m1s09_spring_boot.controller.forms;

import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import com.senai.devinhouse.m1s09_spring_boot.model.enums.AccountType;
import com.senai.devinhouse.m1s09_spring_boot.repository.CustomerRepository;
import com.senai.devinhouse.m1s09_spring_boot.service.CustomerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewAccountForm {

    @NotNull(message = "Customer is required")
    private Integer idCustomer;

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    public NewAccountForm() {
    }

    public NewAccountForm(Integer idCustomer, AccountType accountType) {
        this.idCustomer = idCustomer;
        this.accountType = accountType;
    }

    public Account converter() {
        Account account = new Account();
        CustomerService customerService = new CustomerService(new CustomerRepository());
        Customer customer = customerService.findById(idCustomer);
        account.setCustomer(customer);
        account.setAccountType(accountType);
        return account;
    }
}
