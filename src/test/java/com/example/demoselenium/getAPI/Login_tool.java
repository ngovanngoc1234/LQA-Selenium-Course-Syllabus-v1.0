package com.example.demoselenium.getAPI;

import com.example.demoselenium.addFileCSV.ReadCSV;
import com.example.demoselenium.addFileCSV.WriteExcelExample;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.example.demoselenium.object.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Login_tool implements Serializable {

    WriteExcelExample readListID = new WriteExcelExample();
    List<ReadCSV> arrayListID = new ArrayList<>();
    List<SourceAndResult> sourceAndResults = new ArrayList<>();
    String excelFilePath = "C:\\Users\\LQA\\Desktop\\Output.xlsx";
    Account account = new Account();
    com.example.demoselenium.object.Token token = new Token();

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


    int nameProject = 7600;
    int record = 10;

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
            RestAssured.basePath = "/project/" + nameProject + "/output";

            Response response = RestAssured.given().
                    queryParam("queryingField", "WORKER_NICKNAME")
                    .queryParam("page", i)
                    .log().all()
                    .header("Authorization", "Bearer" + token.getToken())
                    .header("X-AUTH-TOKEN", token.getToken())
                    .contentType(ContentType.JSON)
                    .when()
                    .get();
            List<Content> content = response.jsonPath().get("content");
            List<Content> dataId = mapper.convertValue(content, new TypeReference<List<Content>>() {
            });

            for (Content c : dataId) {
                id = c.getDataId();
                System.out.println(id);
                status = c.getDataStatusLocalName();
                System.out.println(count++);
                ReadCSV readCSV = new ReadCSV(id, examples, food, size, quantity, weight, temperatureC, smell, constitutive, offer, promotion, cartShipper, other, status, workerName);
                arrayListID.add(readCSV);
                id = null;
                status = null;
            }
        }
    }


    @Test(priority = 3)
    public void GetURL() {
        RestAssured.baseURI = "https://coapi.crowdworks.kr";
        String sourceApiUrl = "";
        String resultApiUrl = "";
        for (int i = 0; i < arrayListID.size(); i++) {
            RestAssured.basePath = "/project/" + nameProject + "/output/" + arrayListID.get(i).getID();
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
                    int food1 = 0;
                    int size1 = 0;
                    int sl1 = 0;
                    int ct1 = 0;
                    int c1 = 0;
                    int other1 = 0;
                    int km1 = 0;
                    int h1 = 0;
                    int st1 = 0;
                    int kg1 = 0;
                    int offer1 = 0;
                    for (int k = 0; k < objectList.get(i).length(); k++) {
                        String st = objects.getJSONObject(k).getString("name_K6T9L7");
                        switch (st) {
                            case "사이즈":
                                size1++;
                                String size = objects.getJSONObject(k).getString("text");
                                if (size1 >= 2) {
                                    String size2 = size + "," + arrayListID.get(i).getSize();
                                    arrayListID.get(i).setSize(size2);
                                } else {
                                    arrayListID.get(i).setSize(size);
                                }
                                break;
                            case "구성":
                                ct1++;
                                String ct = objects.getJSONObject(k).getString("text");
                                if (ct1 >= 2) {
                                    String ct2 = ct + "," + arrayListID.get(i).getConstitutive();
                                    arrayListID.get(i).setConstitutive(ct2);
                                } else {
                                    arrayListID.get(i).setConstitutive(ct);
                                }
                                break;
                            case "수량":
                                sl1++;
                                String sl = objects.getJSONObject(k).getString("text");
                                if (sl1 >= 2) {
                                    String sl2 = sl + "," + arrayListID.get(i).getQuantity();
                                    arrayListID.get(i).setQuantity(sl2);
                                } else {
                                    arrayListID.get(i).setQuantity(sl);
                                }
                                break;
                            case "중량/용량":
                                kg1++;
                                String kg = objects.getJSONObject(k).getString("text");
                                if (kg1 >= 2) {
                                    String kg2 = kg + "," + arrayListID.get(i).getWeight();
                                    arrayListID.get(i).setWeight(kg2);
                                } else {
                                    arrayListID.get(i).setWeight(kg);
                                }
                                break;
                            case "온도":
                                c1++;
                                String c = objects.getJSONObject(k).getString("text");
                                if (c1 >= 2) {
                                    String c2 = c + "," + arrayListID.get(i).getTemperatureC();
                                    arrayListID.get(i).setTemperatureC(c2);
                                } else {
                                    arrayListID.get(i).setTemperatureC(c);
                                }
                                break;
                            case "추천":
                                offer1++;
                                String offer = objects.getJSONObject(k).getString("text");
                                if (offer1 >= 2) {
                                    String offer2 = offer + "," + arrayListID.get(i).getOffer();
                                    arrayListID.get(i).setOffer(offer2);
                                } else {
                                    arrayListID.get(i).setOffer(offer);
                                }
                                break;
                            case "프로모션":
                                km1++;
                                String km = objects.getJSONObject(k).getString("text");
                                if (km1 >= 2) {
                                    String km2 = km + "," + arrayListID.get(i).getPromotion();
                                    arrayListID.get(i).setPromotion(km2);
                                } else {
                                    arrayListID.get(i).setPromotion(km);
                                }
                                break;
                            case "매장/포장":
                                st1++;
                                String ship = objects.getJSONObject(k).getString("text");
                                if (st1 >= 2) {
                                    String st2 = ship + "," + arrayListID.get(i).getCartShipper();
                                    arrayListID.get(i).setCartShipper(st2);
                                } else {
                                    arrayListID.get(i).setCartShipper(ship);
                                }
                            case "메뉴":
                                food1++;
                                String food = objects.getJSONObject(k).getString("text");
                                if (food1 >= 2) {
                                    String food2 = food + "," + arrayListID.get(i).getFood();
                                    arrayListID.get(i).setFood(food2);
                                } else {
                                    arrayListID.get(i).setFood(food);
                                }
                                break;
                            case "맛":
                                h1++;
                                String h = objects.getJSONObject(k).getString("text");
                                if (h1 >= 2) {
                                    String h2 = h + "," + arrayListID.get(i).getSmell();
                                    arrayListID.get(i).setSmell(h2);
                                } else {
                                    arrayListID.get(i).setSmell(h);
                                }
                                break;
                            case "기타":
                                other1++;
                                String other = objects.getJSONObject(k).getString("text");
                                if (other1 >= 2) {
                                    String other2 = other + "," + arrayListID.get(i).getOther();
                                    arrayListID.get(i).setOther(other2);
                                } else {
                                    arrayListID.get(i).setOther(other);
                                }
                                break;
                        }
                    }
                }
            } else {
                JSONArray objects = new JSONArray();
                objectList.add(objects);
                arrayListID.get(j).setStatus("no found");
            }
        }
        readListID.writeExcel(arrayListID, excelFilePath);
    }


}
