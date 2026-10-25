package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class JsonPathUtils {

    private  String getJsonResponseAsString(Response response)
    {
        String res=response.getBody().asString();
        return res;
    }

    public <T> T readJson(Response response, String path)
    {
        String res=getJsonResponseAsString(response);
        return JsonPath.read(res, path);
    }

    public <T> List<T> readJsonList(Response response, String path)
    {
        String res=getJsonResponseAsString(response);
        return JsonPath.read(res, path);
    }
}
