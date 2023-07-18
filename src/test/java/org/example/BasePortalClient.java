package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public abstract class BasePortalClient {
    protected String baseAppHost = "swapi.dev";
    protected HttpMethod httpMethod;
    protected RestTemplate restTemplate = new RestTemplateBuilder()
            .build();
    protected ResponseEntity<String> stringResponseEntity;
}
