package com.telecomprovider.customers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Customer {

  private Integer id;
  private String name;
  private List<PhoneNumber> phoneNumbers;

  @JsonCreator
  public Customer(@JsonProperty Integer id,
                  @JsonProperty String name,
                  @JsonProperty List<PhoneNumber> phoneNumbers) {
    this.id = id;
    this.name = name;
    this.phoneNumbers = phoneNumbers;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj ==  null || !(obj instanceof Customer)) {
      return false;
    }
    Customer customer = (Customer) obj;
    return id.equals(customer.id)
            && name.equals(customer.name)
            && phoneNumbers.equals(customer.phoneNumbers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, phoneNumbers);
  }

}
