package com.senai.devinhouse.m1s09_spring_boot.repository;

import java.util.Optional;
import java.util.Set;

public interface CrudRepository<T> {

    //Create
    public boolean create(T t);
    //Read
    public Set<T> findAll();
    public Optional<T> findById(Integer id);
    //Update
    public boolean update(Integer id, T t);
    //Delete
    public boolean delete(Integer id);



}
