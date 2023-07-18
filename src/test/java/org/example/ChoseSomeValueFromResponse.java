package org.example;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import static testData.Variables.SCHEMA_BASEHOST_PATH;
import static io.restassured.RestAssured.given;

@Slf4j
public class ChoseSomeValueFromResponse {
    public String film;
    @Test
    public void getPeopleData() {
        log.info("Performing GET request with URL: " + SCHEMA_BASEHOST_PATH);
        Response response = given().when().get(SCHEMA_BASEHOST_PATH);
        String responseBody = response.getBody().asString();
        film = JsonPath.read(responseBody, "$.films[1]");
        System.out.println(film);
        log.info("Performing GET request with URL: " + film);
        ValidatableResponse responseFilm = given().when().get(film).then().assertThat()
                .statusCode(200);
        responseFilm.log().body();

    }
}
