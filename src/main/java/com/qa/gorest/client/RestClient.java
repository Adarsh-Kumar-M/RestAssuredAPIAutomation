package com.qa.gorest.client;

import com.qa.gorest.frameworkexception.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Properties;

public class RestClient   {
    private static final Logger log = LogManager.getLogger(RestClient.class);
    private static RequestSpecBuilder specBuilder;
    private Properties prop;
    private String baseURI;
    private boolean isAuthorizationAdded=false;

    public RestClient(Properties prop, String baseURI) {
        specBuilder=new RequestSpecBuilder();
        this.prop=prop;
        this.baseURI=baseURI;
    }

    // ****************************Common Reusable Methods*****************

    public void addAuthorization()
    {
        if(!isAuthorizationAdded) {
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
            isAuthorizationAdded = true;
        }
    }

    private  void setContentType(String contentType)
    {
        switch (contentType.toLowerCase().trim()){
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;

            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;

            case "text":
                specBuilder.setContentType(ContentType.TEXT);
                break;

            case "multipart":
                specBuilder.setContentType(ContentType.MULTIPART);
                break;

            case "html":
                specBuilder.setContentType(ContentType.HTML);
                break;

            default:
                log.error("Content Type is not supported");
                throw new APIFrameworkException("===CONTENT TYPE NOT SUPPORTED===");
        }
    }

    // *******************Request Specification Methods*********************************

    /**
     * This method is only for creating a basic Request Spec with BaseURI and TokenId
     *
     * @return
     */
    public RequestSpecification createRequestSpec(boolean includeAuth)
    {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth)
        {
            addAuthorization();
        }
        return specBuilder.build();
    }

    /**
     * This method is only for creating a basic Request Spec with BaseURI, TokenId and headersMap
     * @param headersMap
     * @return
     */
    public RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth)
    {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth)
        {
            addAuthorization();
        }
        if(headersMap!=null){
            specBuilder.addHeaders(headersMap);}
        return specBuilder.build();
    }
// This method is only for creating a basic Request Spec with BaseURI, TokenId, headersMap and queryMap
    public RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryMap, boolean includeAuth)
    {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth)
        {
            addAuthorization();
        }        if(headersMap!=null) {
            specBuilder.addHeaders(headersMap);}
        if(queryMap!=null) {
            specBuilder.addQueryParams(queryMap);}
        return specBuilder.build();
    }

//    This method is only for creating a Post Request Spec with BaseURI, TokenId, headersMap, queryMap and requestBody
    public RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth)
    {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth)
        {
            addAuthorization();
        }
        setContentType(contentType);
        if(requestBody!=null){
            specBuilder.setBody(requestBody);}
        return specBuilder.build();
    }

    public RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth)
    {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth)
        {
            addAuthorization();
        }
        setContentType(contentType);
        if(headersMap!=null){
            specBuilder.addHeaders(headersMap);}
        if(requestBody!=null){
            specBuilder.setBody(requestBody);}
        return specBuilder.build();
    }

    public RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, Map<String, String> queryMap, boolean includeAuth)
    {
        specBuilder.setBaseUri(baseURI);
        if(includeAuth)
        {
            addAuthorization();
        }
        setContentType(contentType);
        if(headersMap!=null){
            specBuilder.addHeaders(headersMap);}
        if(requestBody!=null){
            specBuilder.setBody(requestBody);}
        if(queryMap!=null) {
            specBuilder.addQueryParams(queryMap);}
        return specBuilder.build();
    }

    // ===============================HTTP GET Methods=========================

    public Response getRequest(String serviceUrl, boolean log, boolean includeAuth)
    {
        if(log){
           Response res= RestAssured.given().log().all()
                    .spec(createRequestSpec(includeAuth))
                    .when().get(serviceUrl);
            return res;
        }else {
            Response res= RestAssured.given()
                    .spec(createRequestSpec(includeAuth))
                    .when().get(serviceUrl);
            return res;
        }
    }

    public Response getRequest(String serviceUrl,Map<String, String> headersMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(headersMap, includeAuth))
                    .when().get(serviceUrl);
            return res;
        } else {
            Response res=RestAssured.given()
                    .spec(createRequestSpec(headersMap, includeAuth))
                    .when().get(serviceUrl);
            return res;
        }
    }

    public Response getRequest(String serviceUrl, Map<String , String> headersMap, Map<String, String> queryMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(headersMap, queryMap, includeAuth))
                    .when().get(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec(createRequestSpec(headersMap, queryMap, includeAuth))
                    .when().get(serviceUrl);
            return res;
        }
    }

// ===============================HTTP POST Methods=========================

    public Response postRequest(String serviceUrl, Object requestBody, String contentType, boolean log , boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType, includeAuth))
                    .when().post(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec((createRequestSpec(requestBody, contentType, includeAuth)))
                    .when().post(serviceUrl);
            return res;
        }
    }

    public Response postRequest(String serviceUrl, Object requestBody, String contentType, Map<String, String> headersMap, boolean log, boolean includeAuth)
        {
            if(log)
            {
                Response res=RestAssured.given().log().all()
                        .spec(createRequestSpec(requestBody,contentType, headersMap, includeAuth))
                        .when()
                        .post(serviceUrl);
                return res;
            }
            else
            {
                Response res=RestAssured.given()
                        .spec(createRequestSpec(requestBody,contentType, headersMap, includeAuth))
                        .when()
                        .post(serviceUrl);

                return res;
            }

        }

    public Response postRequest(String serviceUrl, Object requestBody, String contentType,Map<String, String> headersMap, Map<String, String>queryMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType,headersMap, queryMap, includeAuth ))
                    .when().post(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, queryMap, includeAuth))
                    .when().post(serviceUrl);
            return res;
        }
    }

    // ===============================HTTP PUT Methods=========================

    public Response putRequest(String serviceUrl, Object requestBody, String contentType, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType, includeAuth))
                    .when().put(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec((createRequestSpec(requestBody, contentType, includeAuth)))
                    .when().put(serviceUrl);
            return res;
        }
    }

    public Response  putRequest(String serviceUrl, Object requestBody, String contentType, Map<String, String> headersMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                    .when().put(serviceUrl);
            return res;
        }else {
            Response res= RestAssured.given()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                    .when().put(serviceUrl);
            return res;
        }
    }

    public Response  putRequest(String serviceUrl, Object requestBody, String contentType,Map<String, String> headersMap, Map<String, String>queryMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType,headersMap, queryMap, includeAuth ))
                    .when().put(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, queryMap, includeAuth))
                    .when().put(serviceUrl);
            return res;
        }
    }

    // ===============================HTTP PATCH Methods=========================

    public Response patchRequest(String serviceUrl, Object requestBody, String contentType, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType, includeAuth))
                    .when().patch(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec((createRequestSpec(requestBody, contentType, includeAuth)))
                    .when().patch(serviceUrl);
            return res;
        }
    }

    public Response  patchRequest(String serviceUrl, Object requestBody, String contentType, Map<String, String> headersMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                    .when().patch(serviceUrl);
            return res;
        }else {
            Response res= RestAssured.given()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                    .when().patch(serviceUrl);
            return res;
        }
    }

    public Response  patchRequest(String serviceUrl, Object requestBody, String contentType,Map<String, String> headersMap, Map<String, String>queryMap, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(requestBody, contentType,headersMap, queryMap, includeAuth ))
                    .when().patch(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec(createRequestSpec(requestBody, contentType, headersMap, queryMap, includeAuth))
                    .when().patch(serviceUrl);
            return res;
        }
    }
    // ===============================HTTP DELETE Methods=========================
    public Response deleteRequest(String serviceUrl, boolean log, boolean includeAuth){
        if(log){
            Response res=RestAssured.given().log().all()
                    .spec(createRequestSpec(includeAuth))
                    .when().delete(serviceUrl);
            return res;
        }else {
            Response res=RestAssured.given()
                    .spec(createRequestSpec(includeAuth))
                    .when().delete(serviceUrl);
            return res;
        }

    }
}
