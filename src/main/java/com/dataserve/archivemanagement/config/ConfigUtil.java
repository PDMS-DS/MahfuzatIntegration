package com.dataserve.archivemanagement.config;

import jdk.javadoc.doclet.Doclet;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ConfigUtil {

    public   String fetchProperties(String key){
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:config.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {

        }
        return (String) properties.get(key);
    }








}

