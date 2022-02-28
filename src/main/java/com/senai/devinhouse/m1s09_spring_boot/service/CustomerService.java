package com.senai.devinhouse.m1s09_spring_boot.service;

import com.senai.devinhouse.m1s09_spring_boot.model.Customer;
import com.senai.devinhouse.m1s09_spring_boot.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService implements CrudService<Customer> {

    private final CrudRepository<Customer> repository;

    public CustomerService(CrudRepository<Customer> repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(Customer customer) {
        return repository.create(customer);
    }

    @Override
    public Set<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean update(Integer id, Customer customer) {
        return repository.update(id, customer);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }
}
