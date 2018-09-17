package com.telecomprovider.customers.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneNumber {

  private String phoneNumber;
  private Boolean active;

  @JsonCreator
  public PhoneNumber(@JsonProperty(value = "phoneNumber") String phoneNumber,
                     @JsonProperty(value = "active") Boolean active) {
    this.active = active;
    this.phoneNumber = phoneNumber;
  }

  public static PhoneNumber buildFor(String phoneNumber, Boolean active) {
    return new PhoneNumber(phoneNumber, active);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof PhoneNumber)) {
      return false;
    }
    PhoneNumber number = (PhoneNumber) obj;
    return this.phoneNumber.equals(number.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumber);
  }

}
