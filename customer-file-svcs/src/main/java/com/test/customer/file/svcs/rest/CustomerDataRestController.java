package com.test.customer.file.svcs.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.customer.file.model.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomerDataRestController {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @PostMapping("/customer/save")
    public ResponseEntity<String> saveCustomerData(@RequestBody CustomerData customerData) throws JsonProcessingException {
        log.info("Data Received = {}", OBJECT_MAPPER.writeValueAsString(customerData));
        return ResponseEntity.ok().build();
    }

}
