package com.w0n.payments_with_kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentsWithKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsWithKafkaApplication.class, args);
		System.out.println("teste");
	}

}
