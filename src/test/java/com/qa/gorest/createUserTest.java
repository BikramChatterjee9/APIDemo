package com.qa.gorest;

import com.qa.base.baseTest;
import com.qa.constraints.AuthType;
import com.qa.pojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.qa.utils.stringUtils.getRandomEmail;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class createUserTest extends baseTest{

    @Test
    public void m1() throws Exception {

        User user = new User("Priyanka",getRandomEmail(),"female","active");
        Response response = restClient.post(GOREST_BASE_URI,GOREST_USERS_ENDPOINT,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("Created"));
        Assert.assertEquals(response.jsonPath().getString("name"),"Priyanka");
    }

}
