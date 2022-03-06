package com.senai.devinhouse.m1s09_spring_boot.controller.dto;

import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Integer id;
    private String name;
    private String cpf;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
    }

}
