package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestRead {
    String baseUrl = "http://localhost:8081/api";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzcxMDY1LCJleHAiOjE3MTk3NzQ2NjV9.H6cfviL4uonH-lfYZqYWNRZwcfeVEfm4erGf43npMFArHzxqkrG69UaTtWJWZpamXnXgDIYd9VjT8t_JBLXBLg";

    @Test
    public void testReadAlbums() {
        String endpoint = baseUrl + "/albums/3";

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        request.header("Content-Type", "application/json");

        Response response = request.get(endpoint);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

        String albums = response.getBody().jsonPath().getString("title");
        Assert.assertNotNull(albums);
    }
}
