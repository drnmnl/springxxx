package com.accenture.tcf.bars.darren.e.b.manuel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class BarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarsApplication.class, args);
	}

}

