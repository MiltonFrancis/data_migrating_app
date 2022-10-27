package com.fullstackit.data_migrating_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fullstackit.data_migrating_app.customer.CustomerRepository;
import com.fullstackit.data_migrating_app.customer_clone.CustomerCloneRepository;



@SpringBootApplication
public class DataMigratingAppApplication {
	
	@Autowired
	CustomerCloneRepository customerCloneRepository;
    @Autowired
    CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DataMigratingAppApplication.class, args);
	}
	
	@Scheduled(fixedRate = 30000L)
	void monitorMyDb() {
		DatabaseMonitor.monitorChanges(customerCloneRepository, customerRepository);
	}

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name= "scheduling.enabled", matchIfMissing = true)
class SchedulingConfiguration{
	
}
