package com.telecomprovider.customers.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telecomprovider.customers.model.PhoneNumber;
import com.telecomprovider.customers.service.CustomerService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class CustomerControllerTest {

  private MockMvc mockMvc;

  @InjectMocks
  private CustomerController customerController;

  @Mock
  private CustomerService customerService;

  @Before
  public void init() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  }

  @Test
  public void shouldGetAllPhoneNumbers() throws Exception {
    List<PhoneNumber> phoneNumbers = Lists.newArrayList(PhoneNumber.buildFor("111111111", true),
            PhoneNumber.buildFor("222222222", false));

    Mockito.when(customerService.getAllCustomerPhoneNumbers()).thenReturn(phoneNumbers);

    mockMvc.perform(MockMvcRequestBuilders.get("/customer/phone-numbers"))
            .andExpect(status().is(200))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].phoneNumber", is("111111111")))
            .andExpect(jsonPath("$[1].phoneNumber", is("222222222")));


    verify(customerService, times(1)).getAllCustomerPhoneNumbers();
    verifyNoMoreInteractions(customerService);
  }

  @Test
  public void shouldGetCustomerPhoneNumberFromId() throws Exception {
    Integer customerId = 1;
    List<PhoneNumber> phoneNumbers = Lists.newArrayList(PhoneNumber.buildFor("333333333", true),
            PhoneNumber.buildFor("444444444", false));

    Mockito.when(customerService.getCustomerPhoneNumbers(eq(customerId))).thenReturn(phoneNumbers);

    mockMvc.perform(MockMvcRequestBuilders.get("/customer/"+ customerId + "/phone-numbers"))
            .andExpect(status().is(200))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].phoneNumber", is("333333333")))
            .andExpect(jsonPath("$[1].phoneNumber", is("444444444")));


    verify(customerService, times(1)).getCustomerPhoneNumbers(eq(customerId));
    verifyNoMoreInteractions(customerService);
  }

  @Test
  public void shouldActivateCustomerNumberUsingId() throws Exception {
    Integer customerId = 2;
    List<PhoneNumber> phoneNumbers = Lists.newArrayList(PhoneNumber.buildFor("333333333", true),
            PhoneNumber.buildFor("444444444", false));
    PhoneNumber phoneNumberToActivate = PhoneNumber.buildFor("444444444", true);
    ObjectMapper objectMapper = new ObjectMapper();
    String requestBody = objectMapper.writeValueAsString(phoneNumberToActivate);

    Mockito.when(customerService.getCustomerPhoneNumbers(eq(customerId))).thenReturn(phoneNumbers);

    mockMvc.perform(MockMvcRequestBuilders.get("/customer/"+ customerId + "/phone-numbers")
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    verify(customerService, times(1)).getCustomerPhoneNumbers(eq(customerId));
    verifyNoMoreInteractions(customerService);
  }




}
