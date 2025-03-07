package com.example.script.models;

import lombok.*;

@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class EOModel {
    private String firstKey;

    private String entityNumber;

    private String entityCode;

    private String sysEntityCode;

    private String name;

    private String responsible;

    private String source;

    private String section;

    private String industry;

    public EOModel(String firstKey, String entityNumber, String entityCode, String sysEntityCode, String name,
                   String responsible, String source, String section, String industry) {
        this.firstKey = firstKey;
        this.entityNumber = entityNumber;
        this.entityCode = entityCode;
        this.sysEntityCode = sysEntityCode;
        this.name = name;
        this.responsible = responsible;
        this.source = source;
        this.section = section;
        this.industry = industry;
    }

    public String getEntityCode() {
        return entityCode;
    }

    public String getName() {
        return name;
    }
}
