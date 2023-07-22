package org.example;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;
import static testData.Variables.*;
import org.springframework.http.*;

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
    public void CheckSortingByPriceAscWithSpring () {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .build();
        HttpMethod httpMethod = HttpMethod.GET;
        String url = "https://kcentr.ru/content-service/api/desktop/v1/products?visitorUuid=8485f7ea-f2e7-4478-aff3-e8fe3b0f3cf0&cityUuid=deb1d05a-71ce-40d1-b726-6ba85d70d58f&categoryId=televizory&sortType=price_asc&limit=19";
        HttpEntity entity = null;

        log.info("Performing {} request with URL: {},\n"
                + "request entity: {}", httpMethod, url);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                httpMethod,
                entity,
                String.class);

        log.info("Getting response with Status Code = {},\n" +
                "Response Body: {}", response.getStatusCode(), response.getBody());
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
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