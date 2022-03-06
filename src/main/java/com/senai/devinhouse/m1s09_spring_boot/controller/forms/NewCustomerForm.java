package com.senai.devinhouse.m1s09_spring_boot.controller.forms;

import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class NewCustomerForm {

    @NotBlank(message = "Name is required!")
    private String name;
    @CPF
    private String cpf;

    public NewCustomerForm(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public Customer converter(){
        Customer customer = new Customer();
        customer.setName(this.name);
        customer.setCpf(this.cpf);
        return customer;
    }
}
