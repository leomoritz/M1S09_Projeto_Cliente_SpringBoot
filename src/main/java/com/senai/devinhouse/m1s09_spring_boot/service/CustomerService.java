package com.senai.devinhouse.m1s09_spring_boot.service;

import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import com.senai.devinhouse.m1s09_spring_boot.model.customer.Customer;
import com.senai.devinhouse.m1s09_spring_boot.repository.CrudRepository;
import com.senai.devinhouse.m1s09_spring_boot.service.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService implements CrudService<Customer> {

    private final CrudRepository<Customer> repository;

    public CustomerService(CrudRepository<Customer> repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        repository.create(customer);
        return customer;
    }

    @Override
    public Set<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        Optional<Customer> customer = repository.findById(id);

        if (customer.isEmpty()) {
            throw new EntityNotFoundException("Customer with id " + id + " does not exists!");
        }

        return customer.get();
    }

    @Override
    public boolean update(Integer id, Customer newCustomer) {

        if (newCustomer == null) {
            throw new IllegalArgumentException("Customer cannot be empty or null!");
        }

        Customer oldCustomer = findById(id);

        return repository.update(oldCustomer.getId(), newCustomer);
    }

    @Override
    public boolean delete(Integer id) {

        Customer customer = findById(id);

        return repository.delete(customer.getId());
    }
}
