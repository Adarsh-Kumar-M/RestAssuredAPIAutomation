package com.qa.gorest.base;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    // Keeping Endpoints for the Framework :
    public final static String GOREST_ENDPOINT="/public/v2/users/";
    public final static String HTTPBIN_ENDPOINT="/get";


    public final static String GOREST_ENDPOINT_XML="/public/v2/users.xml";
    public final static String FAKESTORE_PRODUCT_ENDPOINT="/products/";


    public ConfigurationManager cm;
   public Properties prop;
   public RestClient res;
   public String baseURI;

   @Parameters({"baseURI"})
   @BeforeTest
    public void setUp(String baseUri) throws IOException {
       RestAssured.filters(new AllureRestAssured());
        cm=new ConfigurationManager();
        prop=cm.initProperties();
//        String baseUri=prop.getProperty("baseUri");
        this.baseURI=baseUri;
    }
}
