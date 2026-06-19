package com.qa.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.restclient.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class baseTest {

    public static RestClient restClient;

    public final static String GOREST_BASE_URI = "https://gorest.co.in";

    public final static String GOREST_USERS_ENDPOINT = "/public/v2/users/";

    @BeforeTest
    public void setUp()
    {
        restClient = new RestClient();
    }

    @BeforeSuite
    public void setUpAllureReport()
    {
        RestAssured.filters(new AllureRestAssured());
    }


}
