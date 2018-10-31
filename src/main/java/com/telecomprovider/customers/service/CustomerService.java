package com.telecomprovider.customers.service;

import com.telecomprovider.customers.database.DAO;
import com.telecomprovider.customers.model.Customer;
import com.telecomprovider.customers.model.PhoneNumber;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

  private DAO<Customer> customerDAO;

  @Autowired
  public CustomerService(DAO<Customer> customerDAO) {
    this.customerDAO = customerDAO;
  }

  public Customer addCustomer(Customer customer) {
    customer.setId(customerDAO.getAll().size() + 1);
    customerDAO.save(customer);
    return customer;
  }

  public List<PhoneNumber> getAllCustomerPhoneNumbers() {
    return customerDAO.getAll()
            .stream()
            .flatMap(customer -> customer.getPhoneNumbers().stream())
            .map(phoneNumbers -> PhoneNumber.buildFor(phoneNumbers.getPhoneNumber(), phoneNumbers.getActive()))
            .collect(Collectors.toList());
  }

  public List<PhoneNumber> getCustomerPhoneNumbers(Integer id) {
    return customerDAO.get(id).getPhoneNumbers()
            .stream()
            .map(phoneNumber -> PhoneNumber.buildFor(phoneNumber.getPhoneNumber(), phoneNumber.getActive()))
            .collect(Collectors.toList());
  }

  public Customer activateCustomerNumber(Integer id, PhoneNumber phoneNumberToActivate) {
    Customer customer = customerDAO.get(id);
    List<PhoneNumber> customerPhoneNumbers = customer.getPhoneNumbers();
    List<PhoneNumber> phoneNumbersUpdated = Lists.newArrayList();
    for (PhoneNumber number : customerPhoneNumbers) {
      if (number.equals(phoneNumberToActivate)) {
        number.setActive(phoneNumberToActivate.getActive());
        phoneNumbersUpdated.add(number);
      } else {
        phoneNumbersUpdated.add(number);
      }
    }
    customer.setPhoneNumbers(phoneNumbersUpdated);
    customerDAO.save(customer);
    return customer;
  }
}
