package com.dataserve.archivemanagement.exception;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponseResult<Object> {
    private Object data;
    private Integer responseCode;
    private String responseMessage;
}
