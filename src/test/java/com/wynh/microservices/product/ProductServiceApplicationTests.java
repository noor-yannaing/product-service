package com.wynh.microservices.product;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer momgodbContainer = new MongoDBContainer("mongo:7.0.5");	
	@LocalServerPort
	private Integer port;
	
	static {
		momgodbContainer.start();
		
	}
	
	@BeforeEach
	void setup(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;  
	}
	
	
	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
					"name": "Product 1",
					"description": "desc 1",
					"price": 100.00
				
				}
				""";
		
		RestAssured.given()
					.contentType("application/json")
					.body(requestBody)
					.when()
					.post("/api/product")
					.then()
					.statusCode(201)
					.body("id", Matchers.notNullValue())
					.body("name", Matchers.equalTo("Product 1"))
					.body("description", Matchers.equalTo("desc 1"))
					.body("price", Matchers.equalTo(100.00)); 
		
	}

}
