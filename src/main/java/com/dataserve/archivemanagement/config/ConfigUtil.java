package com.dataserve.archivemanagement.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class ConfigUtil {

    @Autowired
    private  MessageSource messageSource;

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


    public  String  getLocalMessage(String key, String[] param) {
        return messageSource.getMessage(key, param, LocaleContextHolder.getLocale());
    }

}

