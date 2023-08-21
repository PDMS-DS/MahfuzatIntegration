package com.dataserve.archivemanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomDocument {
    private String source;
    private String fileName;
    private String fileExt;
}
