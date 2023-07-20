package org.example;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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
        String URL = "https://kcentr.ru/content-service/api/desktop/v1/products";
        log.info("Performing GET request with URL: " + URL);
        ValidatableResponse valResponse = given()
                .queryParams(queryParameters)
                .when().get(URL).then()
                .assertThat()
                .statusCode(200)
                .body("products.mainCharacteristics[2].name[0]", equalTo("Диагональ"));
        JsonPath responseAsObjects = valResponse.extract().jsonPath();
        List<Integer> pricesActualSorting = responseAsObjects.getList("products.price");
        List<Integer> pricesExpectedSorting = new ArrayList<>(pricesActualSorting);
        Collections.sort(pricesExpectedSorting);
        System.out.println(pricesActualSorting);
        System.out.println(pricesExpectedSorting);
        System.out.println(queryParameters);
        Assert.assertEquals(pricesActualSorting, pricesExpectedSorting);
    }

    @Test
    public void checkFilterByBrand() {

    }

    @Test
    public void arrayOperations() {
        int[] array = {15, 46, 8, 2};
        for (int i : array) {
            Assert.assertTrue(array[1] == 46);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }
        for (int i : array) {
            System.out.println(i);
        }
        String first = "Jack\fDow";
        String last = "Dickens";
        Math.max(25, 6);
        int time = 11;
        String result = (time < 18) ? "Good morning" : "Good evening";
//        switch (14) {
//            case 12:
//                System.out.println("No");
//                break;
//            case 18:
//                System.out.println("Yes");
//                break;
//            case 13:
//                System.out.println("NoN");
//                break;
//            default:
//                System.out.println("default");
//        int x = 0;
//        while (x > 5) {
//            System.out.println(x);
//            x++;
//        }
//        do {
//            System.out.println(x);
//            x++;
//        }
//        while (x > 5);
    }
}