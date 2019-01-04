package com.accenture.tcf.bars.login.server.darren.e.b.manuel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.accenture.tcf.bars.login.server.darren.e.b.manuel")
@EnableJpaRepositories("com.accenture.tcf.bars.login.server.darren.e.b.manuel")
@SpringBootApplication
@EnableEurekaClient
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

