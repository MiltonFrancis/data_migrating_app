package com.fullstackit.data_migrating_app.customer;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String address;
    @Column(name="last_modified")
    LocalDateTime lastModified;
    
 public Customer () {}
    
    public Customer(int id, String name, String address, LocalDateTime lastModified) {
    	this.id = id;
    	this.name = name;
    	this.address = address;
    	this.lastModified = lastModified;
    }
}