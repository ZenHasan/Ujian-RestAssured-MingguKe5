package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUsers {

    String baseUrl = "http://localhost:8081/api";

    @Test
    public void testGetUserProfile() {
        String endpoint = baseUrl+"/users/me";

        String tokenProfile = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiaWF0IjoxNzE5NzYwNzQ3LCJleHAiOjE3MTk3NjQzNDd9.0wfFbzS-4g8jF-YCSxaITz9siuZjNjmRvnusxIG7U-n9jgs7wyUIP8LL-Qpb73xyjuvq--M0Tx0p10QE_VXyaw";

        RequestSpecification requestBody = RestAssured.given();
        requestBody.header("Authorization", "Bearer " + tokenProfile);
        requestBody.header("Content-Type", "application/json");

        Response response = requestBody.get(endpoint);
        Assert.assertEquals(response.getStatusCode(), 200);

        String firstName = response.getBody().jsonPath().getString("firstName");
        String lastName = response.getBody().jsonPath().getString("lastName");
        Assert.assertEquals(firstName, "m.zien");
        Assert.assertEquals(lastName, "hasan");
}
}
