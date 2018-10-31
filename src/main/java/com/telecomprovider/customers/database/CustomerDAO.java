package com.telecomprovider.customers.database;

import com.telecomprovider.customers.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerDAO implements DAO<Customer> {

  private static Map<Integer, Customer> customers = new HashMap<>();

//  @PostConstruct
//  public void postConstruct() {
//    customers = new HashMap<>();
//    List<PhoneNumber> customerOneNumbers = Arrays.asList(new PhoneNumber("111111111", true),
//            new PhoneNumber("222222222", false));
//
//    List<PhoneNumber> customerTwoNumbers = Arrays.asList(new PhoneNumber("333333333", true),
//            new PhoneNumber("444444444", true));
//
//    Customer customerOne = new Customer(1, "John", customerOneNumbers);
//    Customer customerTwo = new Customer(2, "Sarah", customerTwoNumbers);
//
//    customers.put(customerOne.getId(), customerOne);
//    customers.put(customerTwo.getId(), customerTwo);
//  }

  @Override
  public Customer get(Integer id) {
    return customers.get(id);
  }

  @Override
  public List<Customer> getAll() {
    return new ArrayList<>(customers.values());
  }

  @Override
  public Customer save(Customer customer) {
    return customers.put(customer.getId(), customer);
  }

}
