package org.example;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import java.util.Collections;
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
                .statusCode(200)
                .body("products.mainCharacteristics[2].name[0]", equalTo("Диагональ"));
        JsonPath responseAsObjects = valResponse.extract().jsonPath();
        List<Integer> pricesActualSorting = responseAsObjects.getList("products.price");
        List<Integer> pricesExpectedSorting = new ArrayList<>(pricesActualSorting);
        Collections.sort(pricesExpectedSorting);
        System.out.println(pricesActualSorting);
        System.out.println(pricesExpectedSorting);
        Assert.assertEquals(pricesActualSorting, pricesExpectedSorting);
    }

    @Test
    public void arrayOperations(){
        int[] array = {15, 46, 8 ,2};
        for (int i: array)
        Assert.assertTrue(array[0] > array[3]);
    }
}