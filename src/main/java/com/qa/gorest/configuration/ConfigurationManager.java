package com.qa.gorest.configuration;

import com.qa.gorest.frameworkexception.APIFrameworkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Logger log= LogManager.getLogger(ConfigurationManager.class);
    private Properties prop;
    private FileInputStream fp;

    public Properties initProperties() throws IOException
    {

        prop=new Properties();

        String envName=System.getProperty("env");

        log.info("Environment Name is : "+envName);

        if(envName==null)
        {
            log.info("Since env name is not passed running the test cases in default env.");
            fp=new FileInputStream("src/test/resources/config/config.properties");
        }
        else
        {
            switch (envName.trim().toLowerCase())
            {

                case "uat":
                    log.info("Running the test cases in environment:" +envName);
                    fp=new FileInputStream("src/test/resources/config/config_uat.properties");
                    break;

                case "prod":
                    log.info("Running the test cases in environment:" +envName);
                    fp=new FileInputStream("src/test/resources/config/config_prod.properties");
                    break;

                case "stage":
                    log.info("Running the test cases in environment:" +envName);
                    fp=new FileInputStream("src/test/resources/config/config_stage.properties");
                    break;

                default:
                    log.error("Environment is not supported : "+envName);
                    throw new APIFrameworkException("===INCORRECT ENVIRONMENT NAME===");
            }

        }

        prop.load(fp);

        return prop;

    }

}
