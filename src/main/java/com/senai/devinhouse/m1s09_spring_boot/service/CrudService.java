package com.senai.devinhouse.m1s09_spring_boot.service;

import com.senai.devinhouse.m1s09_spring_boot.model.account.Account;
import java.util.Set;

public interface CrudService<T> {

    //Create
    public T create(T t);

    //Read
    public Set<T> findAll();
    public T findById(Integer id);
    //Update
    public boolean update(Integer id, T t);
    //Delete
    public boolean delete(Integer id);

}
