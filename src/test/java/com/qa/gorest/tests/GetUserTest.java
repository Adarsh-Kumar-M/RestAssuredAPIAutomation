package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatusCode;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;

public class GetUserTest  extends BaseTest {
    @BeforeMethod
    public void getUserSUp()
    {
        res = new RestClient(prop, baseURI);
    }

    @Test(priority = 3)
    public void getAllUsersTest()
    {
        res.getRequest(GOREST_ENDPOINT, true, true)
              .then().log().all()
              .assertThat()
              .statusCode(APIHttpStatusCode.OK_200.getCode());
    }

    @Test(priority = 2)
    public void getSingleUser()
    {
        res.getRequest(GOREST_ENDPOINT+8684572, true, true)
                .then().log().all().assertThat()
                .statusCode(APIHttpStatusCode.OK_200.getCode())
                .and()
                .body("id", equalTo(8684572));
    }

    @Test(priority = 1)
    public void getUserWithQueryParams()
    {
        Map<String, String> queryMap=new HashMap<String, String>();
        queryMap.put("status", "active");
        queryMap.put("gender", "female");
        res.getRequest(GOREST_ENDPOINT, null, queryMap, true, true)
                .then().log().all()
                .statusCode(APIHttpStatusCode.OK_200.getCode());
    }
}
