package org.example;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestEmployeeApi {

    static String TEST_URL = "http://employeecrud-env.eba-33j3icdw.us-east-1.elasticbeanstalk.com";
    String id = "";

    //PLEASE RUN THIS METHOD ONE BY ONE
    @Test(priority = 1)
    void createEmployee(){
        RestAssured.baseURI=TEST_URL;
        RequestSpecification httprequest = RestAssured.given();
        JSONObject reqparam = new JSONObject();
        reqparam.put("name","Test");
        reqparam.put("title","Test Title");
        httprequest.header("Content-Type","application/json");
        httprequest.body(reqparam.toString());
        Response response = httprequest.request(Method.POST,"/api/v1/employees");
        id = response.getBody().jsonPath().get("id").toString();
        String res = response.getBody().asString();
        System.out.println("Response is " + res);
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);
    }
    @Test(priority = 2)
    void getEmployee(){
        RestAssured.baseURI=TEST_URL;
        RequestSpecification httprequest = RestAssured.given();
        Response response = httprequest.request(Method.GET,"/api/v1/employees");
        String res = response.getBody().asString();
        System.out.println("Response is " + res);
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }
    //
    @Test(priority = 3)
    void updateEmployee(){
        RestAssured.baseURI=TEST_URL;
        RequestSpecification httprequest = RestAssured.given();
        JSONObject reqparam = new JSONObject();
        reqparam.put("name","Test");
        reqparam.put("title","Update Test Title");
        httprequest.header("Content-Type","application/json");
        httprequest.body(reqparam.toString());
        Response response = httprequest.request(Method.PUT,"/api/v1/employees/"+id);
        String res = response.getBody().asString();
        System.out.println("Response is " + res);
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test(priority = 4)
    void deleteEmployee(){
        RestAssured.baseURI=TEST_URL;
        RequestSpecification httprequest = RestAssured.given();
        Response response = httprequest.request(Method.DELETE,"/api/v1/employees/"+id);
        String res = response.getBody().asString();
        System.out.println("Response is " + res);
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,200);
    }
}
