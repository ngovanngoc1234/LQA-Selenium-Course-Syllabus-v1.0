package com.example.demoselenium.getAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.Serializable;

public class GetAPI implements Serializable {
    public Response Get(String token){
        return RestAssured.given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get();
    }
}
