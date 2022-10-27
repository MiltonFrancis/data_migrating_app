package com.fullstackit.data_migrating_app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.fullstackit.data_migrating_app.customer.Customer;
import com.fullstackit.data_migrating_app.customer.CustomerRepository;
import com.fullstackit.data_migrating_app.customer_clone.CustomerCloneRepository;

@Component
public class DatabaseMonitor {

    
	public static void monitorChanges(CustomerCloneRepository customerCloneRepository, CustomerRepository customerRepository) {
		
		List<Customer> customers = customerRepository.findAll();
		
		for (Customer c: customers) {
			com.fullstackit.data_migrating_app.customer_clone.Customer existingCustomer;
			
			try {
				existingCustomer = customerCloneRepository.findById(c.getId()).get();
			}catch(NoSuchElementException e) {
				existingCustomer = null;
			}
			
			
			if(existingCustomer != null) { // if record already exist migrate changes if there are any
				LocalDateTime dt = existingCustomer.getLastModified();
				if (c.getLastModified().isAfter(dt)) {
					com.fullstackit.data_migrating_app.customer_clone.Customer cc = new com.fullstackit.data_migrating_app.customer_clone.
							Customer(c.getId(),c.getName(),c.getAddress(),c.getLastModified());
					customerCloneRepository.save(cc);
				}
			}else { // if record does not exist in clone table create new record in said table
				com.fullstackit.data_migrating_app.customer_clone.Customer cc = new com.fullstackit.data_migrating_app.customer_clone.
						Customer(c.getId(),c.getName(),c.getAddress(),c.getLastModified());
				customerCloneRepository.save(cc);
			}
			
		}
		System.out.println(customerRepository.findAll());
		System.out.println(customerCloneRepository.findAll());
	}

}
