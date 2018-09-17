package com.telecomprovider.customers.database;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DAO<T> {

  T get(Integer id);

  List<T> getAll();

  T save(T t);
}
