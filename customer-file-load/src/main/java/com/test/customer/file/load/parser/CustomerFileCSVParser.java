package com.test.customer.file.load.parser;

import com.test.customer.file.model.CustomerData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class CustomerFileCSVParser {

    private static final int EXPECTED_FIELDS = 8;

    private CustomerData parseRow(String row) {
        String[] tokens = row.split(",");
        if(tokens.length != EXPECTED_FIELDS) {
            throw new CustomerFileCSVParserException(
                    String.format("Invalid Number of Tokens Expected %s found %s Line: %s", EXPECTED_FIELDS, tokens.length,
                            row)
            );
        }
        return CustomerData.builder()
                .customerRef(tokens[0])
                .customerName(tokens[1])
                .addressLine1(tokens[2])
                .addressLine2(tokens[3])
                .town(tokens[4])
                .county(tokens[5])
                .country(tokens[6])
                .postcode(tokens[7])
                .build();
    }

    public List<CustomerData> loadCSVFile(String filename) throws IOException {
        final List<CustomerData> customerDataList = new LinkedList<>();
        File csvFile = new File(filename);
        if(!csvFile.exists()) {
            throw new CustomerFileCSVParserException(
                    String.format("File %s does not exist", filename)
            );
        }
        try(Stream<String> lines = Files.lines(csvFile.toPath())) {
            lines.forEach(
                    line -> customerDataList.add(parseRow(line))
            );
        }
        return customerDataList;
    }
}
