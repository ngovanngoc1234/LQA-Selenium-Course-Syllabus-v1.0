package com.example.demoselenium.getAPI;

import com.example.demoselenium.addFileCSV.ReadCSV;
import com.example.demoselenium.addFileCSV.WriteExcelExample;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.util.JSON;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import object.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//import com.mashape.unirest.http.ObjectMapper;

public class Login_tool implements Serializable {

    WriteExcelExample readListID = new WriteExcelExample();
    List<ReadCSV> arrayListID = new ArrayList<>();
    List<SourceAndResult> sourceAndResults = new ArrayList<>();
    String excelFilePath = "C:\\Users\\LQA\\Desktop\\Output.xlsx";
    Account account = new Account();
    object.Token token = new Token();

    String examples = "";
    String food = "";
    String size = "";
    String quantity = "";
    String weight = "";
    String temperatureC = "";
    String smell = "";
    String constitutive = "";
    String offer = "";
    String promotion = "";
    String cartShipper = "";
    String other = "";
    String status = "";
    String id = "";
    String workerName = "";


    int num = 7600;
    int record = 3;

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

        String tokens = response.jsonPath().get("token");
        System.out.println("token = " + tokens);
        this.token.setToken(tokens);

    }

    @Test(priority = 2)
    public void getDataID() throws IOException, InterruptedException {
        int count = 0;
        ObjectMapper mapper = new ObjectMapper();
        RestAssured.baseURI = "https://coapi.crowdworks.kr";
        for (int i = 1; i < record; i++) {
            RestAssured.basePath = "/project/" + num + "/output";

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
                id = c.getDataId();
                System.out.println(id);
                System.out.println(count++);
                ReadCSV readCSV = new ReadCSV(id, examples, food, size, quantity, weight, temperatureC, smell, constitutive, offer, promotion, cartShipper, other, status, workerName);
                arrayListID.add(readCSV);
                id = null;
            }
        }
    }


    @Test(priority = 3)
    public void GetURL() {
        RestAssured.baseURI = "https://coapi.crowdworks.kr";
        String sourceApiUrl = "";
        String resultApiUrl = "";
        for (int i = 0; i < arrayListID.size(); i++) {
            RestAssured.basePath = "/project/" + num + "/output/" + arrayListID.get(i).getID();
            Response response = RestAssured.given().log().all()
                    .header("Authorization", "Bearer" + token.getToken())
                    .header("X-AUTH-TOKEN", token.getToken())
                    .contentType(ContentType.JSON)
                    .when()
                    .get();
            sourceApiUrl = response.jsonPath().get("sourceApiUrl");
            resultApiUrl = response.jsonPath().get("resultApiUrl");


            System.out.println("source" + sourceApiUrl);
            System.out.println("result" + resultApiUrl);
            SourceAndResult andResult = new SourceAndResult(sourceApiUrl, resultApiUrl);
            sourceAndResults.add(andResult);
            sourceApiUrl = null;
            resultApiUrl = null;

        }
    }

    int p = 0;

    @Test(priority = 4)
    public void Get_sourceApiUrl() throws UnirestException, IOException {
        Unirest.setTimeouts(0, 0);
        for (int i = 0; i < sourceAndResults.size(); i++) {
            HttpResponse<String> response = Unirest.get(sourceAndResults.get(i).getSource()).asString();
            String sourceName = response.getBody();
            JSONObject objectJson = new JSONObject(sourceName);
            String example = objectJson.getString("menu_name");
            System.out.println(example);
            arrayListID.get(i).setExample(example);
            example = null;
            p++;
            System.out.println(p);
        }
    }


    List<JSONArray> objectList = new ArrayList<>();

    @Test(priority = 5)
    public void Get_resultApiUrl() throws UnirestException, IOException {
        Unirest.setTimeouts(0, 0);
        for (int j = 0; j < sourceAndResults.size(); j++) {
            String value = sourceAndResults.get(j).getResult();
            if (value != null) {
                HttpResponse<String> response = Unirest.get(value).asString();
                String s = response.getBody();
                JSONObject jsonObject = new JSONObject(s);

                JSONArray arrays = jsonObject
                        .getJSONArray("results")
                        .getJSONArray(1).getJSONObject(0).getJSONArray("name_2WV7BM");

                String jo = jsonObject.getJSONObject("works").getString("workerName");
                arrayListID.get(j).setWorkerName(jo);
                jo = null;

                if (arrays.length() == 0) {
                    arrayListID.get(j).setStatus("non-workable");
                    objectList.add(arrays);
                    continue;
                }

                System.out.println(arrays);
                System.out.println(" length arr " + arrays.length());
                objectList.add(arrays);

                for (int i = 0; i < objectList.size(); i++) {
                    JSONArray objects = objectList.get(i);
                    for (int k = 0; k < objectList.get(i).length(); k++) {
                        String st = objects.getJSONObject(k).getString("name_K6T9L7");
                        switch (st) {
                            case "사이즈":
                                String size = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setSize(size);
                                size = null;
                                break;
                            case "구성":
                                String ct = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setConstitutive(ct);
                                ct = null;
                                break;
                            case "수량":
                                String sl = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setQuantity(sl);
                                sl = null;
                                break;
                            case "중량/용량":
                                String kg = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setWeight(kg);
                                kg = null;
                                break;
                            case "온도":
                                String c = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setTemperatureC(c);
                                c = null;
                                break;
                            case "추천":
                                String offer = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setOffer(offer);
                                offer = null;
                                break;
                            case "프로모션":
                                String km = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setPromotion(km);
                                km = null;
                                break;
                            case "매장/포장":
                                String ship = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setCartShipper(ship);
                                ship = null;
                                break;
                            case "메뉴":
                                String food = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setFood(food);
                                food = null;
                                break;
                            case "맛":
                                String h = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setSmell(h);
                                h = null;
                                break;
                            case "기타":
                                String other = objects.getJSONObject(k).getString("text");
                                arrayListID.get(i).setOther(other);
                                other = null;
                                break;
                        }
                    }
                }
                arrays = null;
                s = null;
            } else {
                JSONArray objects = new JSONArray();
                objectList.add(objects);
                arrayListID.get(j).setStatus("no found");
                objects = null;
                System.out.println("AAAAAAAAAAAAAAA");
            }
        }
        readListID.writeExcel(arrayListID, excelFilePath);

    }


}
