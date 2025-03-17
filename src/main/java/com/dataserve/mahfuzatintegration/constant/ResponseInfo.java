package com.dataserve.mahfuzatintegration.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseInfo {

    SUCCESS(200, "Success",
            "\u062a\u0645\u062a \u0627\u0644\u062a\u0646\u0641\u064a\u0630 \u0628\u0646\u062c\u0627\u062d"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error",
            "\u062d\u062f\u062b \u062e\u0637\u0623 \u0627\u062b\u0646\u0627\u0621 \u0627\u0644\u062a\u0646\u0641\u064a\u0630"),
    NO_DATA_FOUND(600, "No data found",
            "\u0644\u0627 \u064a\u0648\u062c\u062f \u0628\u064a\u0627\u0646\u0627\u062a");

    private final int statusCode;
    private final String message;
    private final String messageAr;
	
    
    

}
