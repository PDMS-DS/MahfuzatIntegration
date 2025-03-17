package com.dataserve.mahfuzatintegration.util;

import org.springframework.context.i18n.LocaleContextHolder;

public class LocalizationUtil {

    public static String getLocalizedValue(String arValue, String enValue) {
        String currentLocale = LocaleContextHolder.getLocale().getLanguage();
        return "ar".equalsIgnoreCase(currentLocale) ? arValue : enValue;
    }
}