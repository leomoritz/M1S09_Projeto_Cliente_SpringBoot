package com.senai.devinhouse.m1s09_spring_boot.controller;

import com.senai.devinhouse.m1s09_spring_boot.controller.dto.CustomerDTO;
import com.senai.devinhouse.m1s09_spring_boot.controller.forms.NewCustomerForm;
import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import com.senai.devinhouse.m1s09_spring_boot.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CrudService<Customer> service;

    public CustomerController(CrudService<Customer> service) {
        this.service = service;
    }

    @GetMapping("/getCustomerList")
    public ResponseEntity<Set<CustomerDTO>> getCustomerList() {
        Set<CustomerDTO> customersDto = service.findAll().stream().map(CustomerDTO::new).collect(Collectors.toSet());
        return ResponseEntity.ok().body(customersDto);
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        Customer customer = service.findById(id);
        CustomerDTO customerDto = new CustomerDTO(customer);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping(value = "/registerCustomer")
    public ResponseEntity<CustomerDTO> registerCustomer(@Valid @RequestBody NewCustomerForm customerForm) {
        Customer customer = service.create(customerForm.converter());
        CustomerDTO customerDTO = new CustomerDTO(customer);
        return ResponseEntity.ok().body(customerDTO);
    }

    @PutMapping("/updateCustomerById/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Integer id, @Valid @RequestBody NewCustomerForm customerForm) {
        Customer customer = customerForm.converter();
        service.update(id, customer);
        CustomerDTO customerDTO = new CustomerDTO(customer);
        return ResponseEntity.ok().body(customerDTO);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok().body("Customer with " + id + " successfully removed");
    }

}
