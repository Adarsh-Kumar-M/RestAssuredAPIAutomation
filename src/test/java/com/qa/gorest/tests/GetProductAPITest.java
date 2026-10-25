package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatusCode;
import com.qa.gorest.utils.JsonPathUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GetProductAPITest extends BaseTest {
    private static final Logger log= LogManager.getLogger(GetProductAPITest.class);

    @BeforeMethod
    public void getProductSetup()
    {
        res = new RestClient(prop, baseURI);
    }

    @Test
    public void getAllProductsInfoFakeStore()
    {
       Response response = res.getRequest(FAKESTORE_PRODUCT_ENDPOINT, true, false);
       int statusCode=response.statusCode();
        Assert.assertEquals(statusCode, APIHttpStatusCode.OK_200.getCode());
        JsonPathUtils js=new JsonPathUtils();
        List<Float> rateList = js.readJsonList(response, "$..[?(@.rate>3)].rate");
        log.info(rateList);

    }
}
