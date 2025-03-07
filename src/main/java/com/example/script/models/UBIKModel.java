package com.example.script.models;

import lombok.*;

@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UBIKModel {
    private String entityCode;

    private String firstKey;

    public UBIKModel(String entityCode, String firstKey) {
        this.entityCode = entityCode;
        this.firstKey = firstKey;
    }
}
