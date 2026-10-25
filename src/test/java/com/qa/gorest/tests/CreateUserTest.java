package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatusCode;
import com.qa.gorest.pojo.UserPOJO;
import com.qa.gorest.utils.ExcelUtils;
import com.qa.gorest.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class CreateUserTest  extends BaseTest {
    private static final Logger log= LogManager.getLogger(CreateUserTest.class);
    @BeforeMethod
    public void createUserSetup()
    {
        res = new RestClient(prop, baseURI);
    }

    @DataProvider
    public Object[][] createUserDataProvider()
    {
        Object obj[][]={{"Mohan", "male", "active"},{"Meena", "female", "active"},{"Raj", "male","inactive"}};
        return obj;
    }

    @Test(dataProvider = "createUserDataProvider")
    public void createUserTest(String name, String gender, String status){
        UserPOJO up=new UserPOJO(name, StringUtils.generateEmailId(),gender, status);
       int userId= res.postRequest(GOREST_ENDPOINT, up, "json", true, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatusCode.CREATED_201.getCode())
                .extract().path("id");
        log.info("User id is : "+userId);

        // 2. Fetching the created user:
        res.getRequest(GOREST_ENDPOINT+userId, true, true )
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatusCode.OK_200.getCode())
                .body("id", equalTo(userId));
    }

    @DataProvider
    public Object[][] getDataFromSheet()
    {
        Object obj[][]= ExcelUtils.getTestDataFromSheet("Users");
        return obj;
    }



    @Test(dataProvider = "getDataFromSheet")
    public void createUserTestWithExcelData(String name, String gender, String status){
        UserPOJO up=new UserPOJO(name, StringUtils.generateEmailId(),gender, status);
        int userId= res.postRequest(GOREST_ENDPOINT, up, "json", true, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatusCode.CREATED_201.getCode())
                .extract().path("id");
        log.info("User id is : "+userId);

        // 2. Fetching the created user:
        res.getRequest(GOREST_ENDPOINT+userId, true, true )
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatusCode.OK_200.getCode())
                .body("id", equalTo(userId));
    }
}
