package com.fullstackit.data_migrating_app.model;

import lombok.Data;


import java.util.List;



@Data
public class Response {
    List<com.fullstackit.data_migrating_app.customer_clone.CustomerCloneRepository> customer_clones;
    List<com.fullstackit.data_migrating_app.customer.CustomerRepository> customer;
}