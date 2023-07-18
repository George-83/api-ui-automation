package org.example;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetPeopleInfoAssured {
    @Test
    public void getPeopleInfoTestAssured () {
        String URL = "https://swapi.dev/api/people/1/";
        ValidatableResponse response = given().when().get(URL).then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Luke Skywalker"));
        response.log().body();
    }
}
