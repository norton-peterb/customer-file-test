package com.test.customer.file.load;

import com.test.customer.file.load.parser.CustomerFileCSVParser;
import com.test.customer.file.load.sender.CustomerDataSender;
import com.test.customer.file.model.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class CustomerFileLoadApplication implements CommandLineRunner {

    private final CustomerFileCSVParser customerFileCSVParser;
    private final CustomerDataSender customerDataSender;

    public CustomerFileLoadApplication(CustomerFileCSVParser customerFileCSVParser,
                                       CustomerDataSender customerDataSender) {
        this.customerFileCSVParser = customerFileCSVParser;
        this.customerDataSender = customerDataSender;
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerFileLoadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(args.length != 1) {
            System.out.println("Invalid Number of command line arguments");
            System.out.println("Usage: java -jar customer-file-load.jar <CSV Filename>");
        }
        String filename = args[0];
        List<CustomerData> customerDataList = customerFileCSVParser.loadCSVFile(filename);
        System.out.printf("Loaded %d line(s) from file %s", customerDataList.size(), filename);
        customerDataSender.sendCustomerData(customerDataList);
    }
}
