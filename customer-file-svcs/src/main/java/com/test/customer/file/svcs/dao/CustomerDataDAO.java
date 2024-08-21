package com.test.customer.file.svcs.dao;

import com.test.customer.file.model.CustomerData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class CustomerDataDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDataDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_SAVE = """
        INSERT INTO CUSTOMER_DATA (REF, NAME, ADDRLINE1, ADDRLINE2, TOWN, COUNTY, COUNTRY, POSTCODE)
        VALUES(?,?,?,?,?,?,?,?)
    """;

    private static final String SQL_GET = """
        SELECT REF, NAME, ADDRLINE1, ADDRLINE2, TOWN, COUNTY, COUNTRY, POSTCODE FROM CUSTOMER_DATA
        WHERE REF = ?
    """;

    private CustomerData mapCustomerData(ResultSet resultSet, int rowNum) throws SQLException {
        return CustomerData.builder()
                .customerRef(resultSet.getString("REF"))
                .customerName(resultSet.getString("NAME"))
                .addressLine1(resultSet.getString("ADDRLINE1"))
                .addressLine2(resultSet.getString("ADDRLINE2"))
                .town(resultSet.getString("TOWN"))
                .county(resultSet.getString("COUNTY"))
                .country(resultSet.getString("COUNTRY"))
                .postcode(resultSet.getString("POSTCODE"))
                .build();
    }

    public CustomerData getCustomerData(String customerRef) {
        return jdbcTemplate.queryForObject(SQL_GET, this::mapCustomerData, customerRef);
    }

    public void saveCustomerData(CustomerData customerData) {
        jdbcTemplate.update(SQL_SAVE,
                customerData.getCustomerRef(),
                customerData.getCustomerName(),
                customerData.getAddressLine1(),
                customerData.getAddressLine2(),
                customerData.getTown(),
                customerData.getCounty(),
                customerData.getCountry(),
                customerData.getPostcode()
        );
    }

}
