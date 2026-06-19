package com.qa.gorest;

import com.qa.base.baseTest;
import com.qa.constraints.AuthType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getUserTest extends baseTest {

    @Test
    public void m1() throws Exception {
        Response response = restClient.get(GOREST_BASE_URI,GOREST_USERS_ENDPOINT,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void m2() throws Exception {
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("name","Bela Chopra");
        queryMap.put("status","inactive");
        Response response = restClient.get(GOREST_BASE_URI,GOREST_USERS_ENDPOINT,queryMap,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }


}
