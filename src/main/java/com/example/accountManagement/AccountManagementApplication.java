package com.example.accountManagement;

import com.example.accountManagement.converter.OutputDataToJsonConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AccountManagementApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(AccountManagementApplication.class, args);

		OutputDataToJsonConverter outputDataToJsonConverter =
				applicationContext.getBean(OutputDataToJsonConverter.class);
		outputDataToJsonConverter.sendOutputData();
	}

}
