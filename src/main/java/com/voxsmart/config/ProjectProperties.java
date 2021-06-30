package com.voxsmart.config;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ProjectProperties {
    private Properties project_properties = new Properties();

    public ProjectProperties() {
        try {
            loadProperties();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void loadProperties() throws Exception {
        InputStream inputStream =  Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/project.properties");

        if (inputStream == null)
            throw new FileNotFoundException("property file 'project.properties' not found in the classpath");

        project_properties.load(inputStream);
    }

    public String getApplicationBaseUrl() {
            return project_properties.getProperty("application.base.url");
    }

    public int getSeleniumWaitTimeOutSeconds() {
        String timeout = project_properties.getProperty("selenium.wait.timeout.seconds");
        return Integer.parseInt(timeout);
    }

    public String getBrowserName() {
        return project_properties.getProperty("selenium.browser");
    }

}
