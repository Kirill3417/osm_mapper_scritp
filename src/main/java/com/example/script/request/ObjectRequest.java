package com.example.script.request;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ObjectRequest {
    List<String> entityCodes;
    List<String> districts;
    List<String> subDistricts;
    String types;

    public ObjectRequest(List<String> districts, List<String> subDistricts) {
        this.entityCodes = Collections.singletonList("UGD_Object_1566");
        this.districts = districts;
        this.subDistricts = subDistricts;
        this.types = "OKS";
    }

    public List<String> getEntityCodes() {
        return entityCodes;
    }

    public void setEntityCodes(List<String> entityCodes) {
        this.entityCodes = entityCodes;
    }

    public List<String> getDistricts() {
        return districts;
    }

    public void setDistricts(List<String> districts) {
        this.districts = districts;
    }

    public List<String> getSubDistricts() {
        return subDistricts;
    }

    public void setSubDistricts(List<String> subDistricts) {
        this.subDistricts = subDistricts;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectRequest that = (ObjectRequest) o;
        return Objects.equals(entityCodes, that.entityCodes) && Objects.equals(districts, that.districts) && Objects.equals(subDistricts, that.subDistricts) && Objects.equals(types, that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityCodes, districts, subDistricts, types);
    }

    @Override
    public String toString() {
        return "ObjectRequest{" +
                "entityCodes=" + entityCodes +
                ", districts=" + districts +
                ", subdistricts=" + subDistricts +
                ", types='" + types + '\'' +
                '}';
    }
}
