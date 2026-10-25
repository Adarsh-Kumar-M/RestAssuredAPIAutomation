package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatusCode;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HTTPBinOrgAPITest extends BaseTest {

    @BeforeMethod
    public void httpBinOrgSetup()
    {
        res = new RestClient(prop, baseURI);
    }
    @Test
    public void getALlDataFromHTTPBINAPI()
    {
        res.getRequest(HTTPBIN_ENDPOINT, true, false)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatusCode.OK_200.getCode());
    }
}
