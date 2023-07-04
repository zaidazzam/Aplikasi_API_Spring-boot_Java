package com.magang.aplikasi_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/api/data")
    public ResponseEntity<List<Data>> getData() {
        String query = "SELECT * FROM data_table";
        List<Data> dataList = jdbcTemplate.query(query, (resultSet, rowNum) -> {
            Data data = new Data();
            data.setId(resultSet.getInt("id"));
            data.setProductID(resultSet.getString("productID"));
            data.setProductName(resultSet.getString("productName"));
            data.setAmount(resultSet.getString  ("amount"));
            data.setCustomerName(resultSet.getString("customerName"));
            data.setStatus(resultSet.getInt("status"));
            data.setTransactionDate(resultSet.getString("transactionDate"));
            data.setCreateBy(resultSet.getString("createBy"));
            data.setCreateOn(resultSet.getString("createOn"));
            return data;
        });
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @GetMapping("/api/status")
    public ResponseEntity<List<Status>> getStatus() {
        String query = "SELECT * FROM status_table";
        List<Status> statusList = jdbcTemplate.query(query, (resultSet, rowNum) -> {
            Status status = new Status();
            status.setId(resultSet.getInt("id"));
            status.setName(resultSet.getString("name"));
            return status;
        });
        return new ResponseEntity<>(statusList, HttpStatus.OK);
    }

}
