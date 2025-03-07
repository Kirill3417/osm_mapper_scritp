package com.example.script.models;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@EqualsAndHashCode
@ToString
public class DistrictsAndSubdistrictsModel {
    String administrativeDistrict;
    String regionCode;
    List<String> districtsCode;
    List<String> subdistrictsList;

    public DistrictsAndSubdistrictsModel(String administrativeDistrict, String districtCode, List<String> districtsCode, List<String> subdistrictsList) {
        this.administrativeDistrict = administrativeDistrict;
        this.regionCode = districtCode;
        this.districtsCode = districtsCode;
        this.subdistrictsList = subdistrictsList;
    }

    public DistrictsAndSubdistrictsModel() {
    }

    public void setAdministrativeDistrict(String administrativeDistrict) {
        this.administrativeDistrict = administrativeDistrict;
    }

    public void setRegionCode(String districtCode) {
        this.regionCode = districtCode;
    }

    public void setDistrictsCode(List<String> districtsCode) {
        this.districtsCode = districtsCode;
    }

    public void setSubdistrictsList(List<String> subdistrictsList) {
        this.subdistrictsList = subdistrictsList;
    }

    public String getAdministrativeDistrict() {
        return administrativeDistrict;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public List<String> getDistrictsCode() {
        return districtsCode;
    }

    public List<String> getSubdistrictsList() {
        return subdistrictsList;
    }
}
