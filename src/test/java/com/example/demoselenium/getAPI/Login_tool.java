package com.example.demoselenium.getAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import object.Account;
import object.Content;
import object.DataID;
import object.Token;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import com.mashape.unirest.http.ObjectMapper;

public class Login_tool implements Serializable {

    ReadListID readListID = new ReadListID();
    List<DataID> arrayListID = new ArrayList<>();
    List<String> listSource = new ArrayList<>();
    List<String> listResult = new ArrayList<>();
    String excelFilePath = "C:\\Users\\Admin\\Desktop\\listId.xlsx";
    Account account = new Account();
    object.Token token = new Token();

    String projectID = "7600";

    @Test(priority = 1)
    public void LoginTool() {
        account.setEmail("tien.vu@lqa.com.vn");
        account.setPassword("NVgde2ZSMTW4swB");

        RestAssured.baseURI = "https://coapi.crowdworks.kr";
        RestAssured.basePath = "/login";

        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(account)
                .post();

//        response.prettyPrint();
        String tokens = response.jsonPath().get("token");
        System.out.println("token = " + tokens);
        this.token.setToken(tokens);

    }

    @Test(priority = 2)
    public void getDataID() throws IOException, InterruptedException {
        int count = 0;
        ObjectMapper mapper = new ObjectMapper();
        RestAssured.baseURI = "https://coapi.crowdworks.kr";
        for (int i = 1; i < 5; i++) {
            RestAssured.basePath = "/project/7600/output";

            Response response = RestAssured.given().
                    queryParam("queryingField", "WORKER_NICKNAME")
                    .queryParam("page", i)
                    .log().all()
                    .header("Authorization", "Bearer" + token.getToken())
                    .header("X-AUTH-TOKEN", token.getToken())
                    .contentType(ContentType.JSON)
                    .when()
                    .get();
//        response.prettyPrint();

            List<Content> content = response.jsonPath().get("content");


            List<Content> dataId = mapper.convertValue(content, new TypeReference<List<Content>>() {
            });

            for (Content c : dataId) {
                String id = c.getDataId();
                System.out.println(id);
                System.out.println(count++);
                DataID dataID = new DataID(id);
                arrayListID.add(dataID);
                id = null;
            }
        }
        readListID.writeExcel(arrayListID, excelFilePath);
    }


        @Test(priority = 3)
    public void GetURL() {
        RestAssured.baseURI = "https://coapi.crowdworks.kr";
        String sourceApiUrl = "";
        String resultApiUrl = "";
        for (int i = 0; i < arrayListID.size(); i++) {
            RestAssured.basePath = "/project/" + projectID + "/output/" + arrayListID.get(i).getId();
            Response response = RestAssured.given().log().all()
                    .header("Authorization", "Bearer" + token.getToken())
                    .header("X-AUTH-TOKEN", token.getToken())
                    .contentType(ContentType.JSON)
                    .when()
                    .get();
            response.prettyPrint();
            sourceApiUrl = response.jsonPath().get("sourceApiUrl");
            resultApiUrl = response.jsonPath().get("resultApiUrl");
            System.out.println("source" + sourceApiUrl);
            System.out.println("result" + resultApiUrl);
            listSource.add(sourceApiUrl);
            listResult.add(resultApiUrl);
            sourceApiUrl = null;
            resultApiUrl = null;

        }
    }

        @Test(priority = 4)
    public void Get_sourceApiUrl() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        for (String s : listSource) {
            HttpResponse<String> response = Unirest.get(s).asString();
            System.out.println(response.getBody());

        }
    }

        @Test(priority = 5)
    public void Get_resultApiUrl() throws UnirestException {
        Unirest.setTimeouts(0, 0);
        for (String s : listResult) {
            HttpResponse<String> response = Unirest.get(s).asString();
            System.out.println(response.getBody());
        }
    }


}