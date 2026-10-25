package com.qa.gorest.utils;

public class StringUtils {

    public static String generateEmailId(){
        String emailId="Automation"+Math.random()+"@gmail.com";
        return emailId;
    }

    // If we want to generate random name to create new user
    public static String generateRandomName()
    {
        String name="Automation"+Math.random()+"user";
        return name;
    }
}
