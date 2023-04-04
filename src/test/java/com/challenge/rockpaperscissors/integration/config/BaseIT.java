package com.challenge.rockpaperscissors.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseIT {
	
	@LocalServerPort
    private int port;

    private String baseUrl =  "http://localhost:";
    
    @Autowired
    protected TestRestTemplate restTemplate = new TestRestTemplate();
    
    protected String getUrl(String uri) {
    	return baseUrl + port  + uri;
    }

}
