package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.model.Customer;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CrudService<Customer> service;

    public CustomerController(CrudService<Customer> service) {
        this.service = service;
    }

    @GetMapping("/getCustomerList")
    public ResponseEntity<Set<Customer>> getCustomerList() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findById(id).get());
    }

    @PostMapping(value = "/registerCustomer", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> registerCustomer(@RequestBody @Valid Customer customer, UriComponentsBuilder uriBuilder) {
        Customer newCustomer = service.create(customer);
        URI uri = uriBuilder.path("/registerCustomer").buildAndExpand(newCustomer).toUri();
        return ResponseEntity.created(uri).body("\"id\":" + "\"" + newCustomer.getId() + "\"");
    }

    @PutMapping("/updateCustomerById/{id}")
    public boolean updateCustomerById(@PathVariable Integer id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public boolean deleteCustomerById(@PathVariable Integer id) {
        return service.delete(id);
    }


}
