package org.example;

import groovy.util.logging.Slf4j;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@Slf4j
@SpringBootTest
@Getter
@Setter
public abstract class BaseIntegrationTest {
    protected ResponseEntity<String> stringResponseEntity;

    @Autowired
    protected StarWarsPortalClient starWarsClient;
    private static HttpHeaders defaultHeaders;

    protected static HttpHeaders getDefaultHeaders() {
        return defaultHeaders;
    }
}
