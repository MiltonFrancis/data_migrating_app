package com.fullstackit.data_migrating_app.customer_clone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCloneRepository extends JpaRepository<Customer, Integer> {
}