package com.test.customer.file.svcs.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.customer.file.model.CustomerData;
import com.test.customer.file.svcs.dao.CustomerDataDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CustomerDataRestController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final CustomerDataDAO customerDataDAO;

    public CustomerDataRestController(CustomerDataDAO customerDataDAO) {
        this.customerDataDAO = customerDataDAO;
    }

    @PostMapping("/customer/save")
    public ResponseEntity<String> saveCustomerData(@RequestBody CustomerData customerData) throws JsonProcessingException {
        log.info("Data Received = {}", OBJECT_MAPPER.writeValueAsString(customerData));
        customerDataDAO.saveCustomerData(customerData);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{customerRef}")
    public CustomerData getCustomerData(@PathVariable String customerRef) {
        return customerDataDAO.getCustomerData(customerRef);
    }

}
