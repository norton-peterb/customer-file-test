package com.test.customer.file.load.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.customer.file.model.CustomerData;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class CustomerDataSender {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    private String serviceUrl;

    public CustomerDataSender(@Value("${customer.file.service.url}") String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    private void sendCustomerData(CustomerData customerData) {
        try {
            String customeDataString = OBJECT_MAPPER.writeValueAsString(customerData);
            RequestBody requestBody = RequestBody.create(
                    customeDataString, JSON
            );
            Request request = new Request.Builder()
                    .url("http://localhost:9090/customer/save")
                    .post(requestBody)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                log.info("Response for {} is {}", customeDataString, response.code());
            }
        } catch (IOException exception) {
            log.error("IOException during send", exception);
        }
    }

    public void sendCustomerData(List<CustomerData> customerDataList) {
        customerDataList.forEach(this::sendCustomerData);
    }
}
