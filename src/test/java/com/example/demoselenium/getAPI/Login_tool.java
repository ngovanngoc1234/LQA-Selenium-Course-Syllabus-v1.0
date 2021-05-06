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
import object.Account;
import object.Content;
import object.DataID;
import object.Token;
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
    List<String> listSource = new ArrayList<>();
    List<String> listResult = new ArrayList<>();
    String excelFilePath = "C:\\Users\\LQA\\Desktop\\Output.xlsx";
    Account account = new Account();
    object.Token token = new Token();

    String projectID = "7600";


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

    Scanner sc = new Scanner(System.in);
    int num = 0;
    int record = 0;
    @Test(priority = 1)
    public void LoginTool() {
        System.out.println("input name Project");
        num = Integer.parseInt(sc.nextLine());
        System.out.println("record input");
        record = Integer.parseInt(sc.nextLine());
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
        for (int i = 1; i < record; i++) {
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
                id = c.getDataId();
                System.out.println(id);
                System.out.println(count++);
                ReadCSV readCSV = new ReadCSV(id, examples, food, size, quantity, weight, temperatureC, smell, constitutive, offer, promotion, cartShipper, other, status);
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
            if (response.jsonPath().get("resultApiUrl") != null) {
                resultApiUrl = response.jsonPath().get("resultApiUrl");
            } else {
                resultApiUrl = "no found";
            }

            System.out.println("source" + sourceApiUrl);
            System.out.println("result" + resultApiUrl);
            listSource.add(sourceApiUrl);
            listResult.add(resultApiUrl);
            sourceApiUrl = null;
            resultApiUrl = null;

        }
    }

    int p = 0;

    @Test(priority = 4)
    public void Get_sourceApiUrl() throws UnirestException, IOException {
        Unirest.setTimeouts(0, 0);

        for (int i = 0; i < listSource.size(); i++) {
            HttpResponse<String> response = Unirest.get(listSource.get(i)).asString();
            String sourceName = response.getBody();
            JSONObject objectJson = new JSONObject(sourceName);
            examples = objectJson.getString("menu_name");
            System.out.println(examples);
            arrayListID.get(i).setExample(examples);
            examples = null;
            p++;
            System.out.println(p);
        }
    }


    List<JSONArray> objectList = new ArrayList<>();
    List<JSONArray> getObjectList = new ArrayList<>();

    @Test(priority = 5)
    public void Get_resultApiUrl() throws UnirestException, IOException {
        Unirest.setTimeouts(0, 0);
        for (int j = 0; j < listResult.size(); j++) {
            if (listResult.get(j).equals("no found")) {
                arrayListID.get(j).setOther("no found");
                continue;
            }
            String value = listResult.get(j);
            HttpResponse<String> response = Unirest.get(value).asString();
            String s = response.getBody();
            JSONObject jsonObject = new JSONObject(s);

            JSONArray xxx = jsonObject
                    .getJSONArray("results").getJSONArray(1);

            if (xxx.length() < 1) {
                continue;
            }

            JSONArray arrays = jsonObject
                    .getJSONArray("results")
                    .getJSONArray(1).getJSONObject(0).getJSONArray("name_2WV7BM");

            if (arrays.length() == 0) {
                arrayListID.get(j).setStatus("non-workable");
                continue;
            }
            System.out.println(arrays);
            System.out.println(" length arr " + arrays.length());
            objectList.add(arrays);

            for (int i = 0, objectListSize = objectList.size(); i < objectListSize; i++) {
                JSONArray objects = objectList.get(i);

                for (int k = 0; k < objectList.get(i).length(); k++) {
                    String st = objects.getJSONObject(k).getString("name_K6T9L7");
                    switch (st) {
                        case "사이즈":
                            String size = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setSize(size + ",");
                            size = null;
                            break;
                        case "구성":
                            String ct = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setConstitutive(ct + ",");
                            ct = null;
                            break;
                        case "수량":
                            String sl = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setQuantity(sl + ",");
                            sl = null;
                            break;
                        case "중량/용량":
                            String kg = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setWeight(kg + ",");
                            kg = null;
                            break;
                        case "온도":
                            String c = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setTemperatureC(c + ",");
                            c = null;
                            break;
                        case "추천":
                            String offer = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setOffer(offer + ",");
                            offer = null;
                            break;
                        case "프로모션":
                            String km = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setPromotion(km + ",");
                            km = null;
                            break;
                        case "매장/포장":
                            String ship = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setCartShipper(ship + ",");
                            ship = null;
                            break;
                        case "메뉴":
                            String food = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setFood(food + ",");
                            food = null;
                            break;
                        case "맛":
                            String h = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setSmell(h + ",");
                            h = null;
                            break;
                        case "기타":
                            String other = objects.getJSONObject(k).getString("text");
                            arrayListID.get(j).setSmell(other + ",");
                            other = null;
                            break;
                    }
                }
            }
            arrays = null;
            s = null;
        }
        readListID.writeExcel(arrayListID, excelFilePath);
    }


}
