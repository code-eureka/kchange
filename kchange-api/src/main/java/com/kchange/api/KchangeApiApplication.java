package com.kchange.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication (exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan("com.kchange")
@EnableScheduling
@EnableSwagger2

public class KchangeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KchangeApiApplication.class, args);
	}

}
