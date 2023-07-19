package org.example;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.path.json.JsonPath.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static testData.Variables.*;

@Slf4j
public class GetPeopleInfoTest {
    @Test (description = "Info about Luck Skywalker")
    public void getPeopleData() {
        log.info("Performing GET request with URL: " + SCHEMA_BASEHOST_PATH);
        var response = given().when().get(SCHEMA_BASEHOST_PATH).then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/PeopleInfoSchema.json"));
        response.log().body();
        System.out.print(response);
    }

    @Test
    public void CheckSortingByPriceAsc () {
        String URL = "https://kcentr.ru/content-service/api/desktop/v1/products?visitorUuid=8485f7ea-f2e7-4478-aff3-e8fe3b0f3cf0&cityUuid=deb1d05a-71ce-40d1-b726-6ba85d70d58f&categoryId=televizory&sortType=price_asc&limit=19";
        log.info("Performing GET request with URL: " + URL);
        ValidatableResponse valResponse = given().when().get(URL).then()
                .assertThat()
                .statusCode(200);
//                .body("products.mainCharacteristics[2].name[0]", equalTo("Диагональ"));
//        valResponse.log().body();
        JsonPath responseAsObjects = valResponse.extract().jsonPath();
        List<Double> prices = responseAsObjects.getList("products.price");
        System.out.println(prices);
    }
}

