package com.telecomprovider.customers.service;

import com.telecomprovider.customers.database.DAO;
import com.telecomprovider.customers.model.Customer;
import com.telecomprovider.customers.model.PhoneNumber;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

  @InjectMocks
  private CustomerService customerService;

  @Mock
  private DAO<Customer> customerDAO;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllCustomerPhoneNumbersSucceeds() {
    List<PhoneNumber> customerOneNumbers = Arrays.asList(new PhoneNumber("111111111", true));
    List<PhoneNumber> customerTwoNumbers = Arrays.asList(new PhoneNumber("222222222", true));

    List<Customer> customers = Lists.newArrayList(new Customer(1, "John", customerOneNumbers),
            new Customer(2, "Sarah", customerTwoNumbers));

    List<PhoneNumber> customerNumbers = Lists.newArrayList(new PhoneNumber("111111111", true),
            new PhoneNumber("222222222", true));

    when(customerDAO.getAll()).thenReturn(customers);

    List<PhoneNumber> customerNumbersRetrieved = customerService.getAllCustomerPhoneNumbers();

    Assert.assertEquals(customerNumbers, customerNumbersRetrieved);
  }


  @Test
  public void testGetCustomerPhoneNumbersSucceeds() {
    List<PhoneNumber> customerNumbers = Arrays.asList(new PhoneNumber("111111111", true),
            new PhoneNumber("222222222", true));

    Customer customer = new Customer(1, "John", customerNumbers);

    when(customerDAO.get(eq(1))).thenReturn(customer);

    List<PhoneNumber> customerNumbersRetrieved = customerService.getCustomerPhoneNumbers(customer.getId());

    Assert.assertEquals(customerNumbers, customerNumbersRetrieved);
  }


  @Test
  public void testActivateNumberSucceeds() {
    PhoneNumber phoneNumberActivated = new PhoneNumber("111111111", true);
    List<PhoneNumber> customerNumbers = Arrays.asList(new PhoneNumber("111111111", false));
    Customer customer = new Customer(1, "John", customerNumbers);
    when(customerDAO.get(eq(1))).thenReturn(customer);

    Customer customerUpdated = customerService.activateCustomerNumber(customer.getId(), phoneNumberActivated);

    verify(customerDAO, times(1)).save(customer);
    Assert.assertEquals(phoneNumberActivated.getActive(), customerUpdated.getPhoneNumbers().get(0).getActive());
  }

}
