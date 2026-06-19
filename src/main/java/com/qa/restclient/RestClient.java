package com.qa.restclient;

import com.qa.constraints.AuthType;
import com.qa.manager.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestClient {

    ResponseSpecification responseSpec200 = expect().statusCode(200);
    ResponseSpecification responseSpec204 = expect().statusCode(204);
    ResponseSpecification responseSpec201 = expect().statusCode(201);
    ResponseSpecification responseSpec400 = expect().statusCode(400);
    ResponseSpecification responseSpec200_201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));

    public RequestSpecification setupRequest(String baseURL, AuthType authType, ContentType contentType) throws Exception {
        RequestSpecification request = given().baseUri(baseURL).log().all()
                .contentType(contentType)
                .accept(contentType);

        switch (authType)
        {
            case BEARER_TOKEN:
                request.header("Authorization","Bearer "+ ConfigManager.get("bearerToken"));
                break;
            case NO_AUTH:
                System.out.println("No auth needed");
                break;
            default:
                throw new Exception("Invalid input");
        }

        return request;
    }

    public void applyParam(RequestSpecification request, Map<String,String> queryParams,
                           Map<String,String> pathParams)
    {
        if(queryParams!=null)
        {
            request.queryParams(queryParams);
        }
        if(pathParams!=null)
        {
            request.pathParams(pathParams);
        }
    }

    public Response get(String baseURI,String endPoint,Map<String,String> queryParem,
                        Map<String,String> pathParem,AuthType authType,ContentType contentType) throws Exception {

        RequestSpecification request = setupRequest(baseURI,authType,contentType);
        applyParam(request,queryParem,pathParem);
        return request.get(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public <T>Response post(String baseURI,String endPoint, T body,Map<String,String> queryParem,
                     Map<String,String> pathParem,AuthType authType,ContentType contentType) throws Exception {

        RequestSpecification request = setupRequest(baseURI,authType,contentType);
        applyParam(request,queryParem,pathParem);
        return request.body(body).post(endPoint).then().log().all().spec(responseSpec201).extract().response();
    }

    public <T>Response put(String baseURI,String endPoint, T body,Map<String,String> queryParem,
                            Map<String,String> pathParem,AuthType authType,ContentType contentType) throws Exception {

        RequestSpecification request = setupRequest(baseURI,authType,contentType);
        applyParam(request,queryParem,pathParem);
        return request.body(body).post(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public <T>Response patch(String baseURI,String endPoint, T body,Map<String,String> queryParem,
                           Map<String,String> pathParem,AuthType authType,ContentType contentType) throws Exception {

        RequestSpecification request = setupRequest(baseURI,authType,contentType);
        applyParam(request,queryParem,pathParem);
        return request.body(body).post(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public Response delete(String baseURI,String endPoint,Map<String,String> queryParem,
                             Map<String,String> pathParem,AuthType authType,ContentType contentType) throws Exception {

        RequestSpecification request = setupRequest(baseURI,authType,contentType);
        applyParam(request,queryParem,pathParem);
        return request.delete(endPoint).then().log().all().spec(responseSpec204).extract().response();
    }
}
