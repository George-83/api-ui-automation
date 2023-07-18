package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.Test;

import static org.example.BasePath.API_PEOPLE_NUMBER;
import static org.example.BasePath.SCHEME;


@Component
@Slf4j
public class StarWarsPortalClient extends BasePortalClient {
    public  ResponseEntity<String> getPeopleInfo (HttpHeaders headers) {
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(headers);

    String url = UriComponentsBuilder.newInstance()
            .scheme(SCHEME)
            .host(baseAppHost)
            .path(API_PEOPLE_NUMBER)
            .build()
            .toUriString();
    httpMethod = HttpMethod.GET;

    ResponseEntity<String> response = restTemplate.exchange(
            url,
            httpMethod,
            entity,
            String.class);
    return response;
    }
}
