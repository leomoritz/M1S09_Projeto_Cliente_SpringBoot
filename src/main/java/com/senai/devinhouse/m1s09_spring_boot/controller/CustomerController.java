package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.model.Customer;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CrudService<Customer> service;

    public CustomerController(CrudService<Customer> service) {
        this.service = service;
    }

    @GetMapping("/getCustomerList")
    public Set<Customer> getCustomerList(){
        return service.findAll();
    }

    @GetMapping("/getCustomerById{id}")
    public Customer getCustomerById(@PathVariable Integer id){
       return service.findById(id).get();
    }

    @PostMapping("/registerCustomer")
    public boolean registerCustomer(@RequestBody Customer customer){
        return service.create(customer);
    }

}
