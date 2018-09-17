package com.telecomprovider.customers.Controller;

import com.telecomprovider.customers.model.PhoneNumber;
import com.telecomprovider.customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {

  private CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(value = "phone-numbers", method = RequestMethod.GET)
  public ResponseEntity<List<PhoneNumber>> getAllCustomerPhoneNumbers() {
    List<PhoneNumber> phoneNumbers = customerService.getAllCustomerPhoneNumbers();
    return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
  }

  @RequestMapping(value = "{id}/phone-numbers", method = RequestMethod.GET)
  public ResponseEntity<List<PhoneNumber>> getCustomerPhoneNumbers(@PathVariable Integer id) {
    List<PhoneNumber> phoneNumbers = customerService.getCustomerPhoneNumbers(id);
    return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
  }

  @RequestMapping(value = "{id}/phone-numbers/activate", method = RequestMethod.PATCH)
  public ResponseEntity<Void> activateNumber(@PathVariable Integer id, @RequestBody PhoneNumber phoneNumber) {
    customerService.activateCustomerNumber(id, phoneNumber);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
