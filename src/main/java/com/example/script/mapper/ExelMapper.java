package com.example.script.mapper;

import com.example.script.models.DistrictsAndSubdistrictsModel;
import com.example.script.models.EOModel;
import com.example.script.models.UBIKModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExelMapper {

    public List<EOModel> mapEO(String filePath) throws IOException {
        List<EOModel> entities = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet("ЕО");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String firstKey = getCellValue(row, 0);
                    String entityNumber = getCellValue(row, 1);
                    String entityCode = getCellValue(row, 2);
                    String sysEntityCode = getCellValue(row, 3);
                    String name = getCellValue(row, 4);
                    String responsible = getCellValue(row, 5);
                    String source = getCellValue(row, 6);
                    String section = getCellValue(row, 7);
                    String industry = getCellValue(row, 8);

                    EOModel entity = new EOModel(firstKey, entityNumber, entityCode, sysEntityCode, name,
                            responsible, source, section, industry);
                    entities.add(entity);
                }
            }
        }
        return entities;
    }

    public List<UBIKModel> mapUBIK(String filePath) throws IOException {
        List<UBIKModel> result = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet("УБИК");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String entityCode = getCellValue(row, 0);
                    String firstKey = getCellValue(row, 1);
                    result.add(new UBIKModel(entityCode, firstKey));
                }
            }
        }
        return result;
    }

    public List<DistrictsAndSubdistrictsModel> mapDistrictsAndSubdistricts(String filePath) throws IOException {
        List<DistrictsAndSubdistrictsModel> districtsList = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet("Лист1");
            DistrictsAndSubdistrictsModel currentDistrict = null;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String administrativeDistrict = getCellValue(row, 0);
                    String districtCode = getCellValue(row, 1);
                    String districtName = getCellValue(row, 2);
                    String subdistrictCode = getCellValue(row, 3);

                    if (administrativeDistrict != null && !administrativeDistrict.isEmpty()) {
                        if (currentDistrict != null) {
                            districtsList.add(currentDistrict);
                        }
                        currentDistrict = new DistrictsAndSubdistrictsModel();
                        currentDistrict.setAdministrativeDistrict(administrativeDistrict);
                        currentDistrict.setRegionCode(districtCode);
                        currentDistrict.setDistrictsCode(new ArrayList<>());
                        currentDistrict.setSubdistrictsList(new ArrayList<>());
                    }

                    if (currentDistrict != null) {
                        if (districtName != null && !districtName.isEmpty()) {
                            currentDistrict.getDistrictsCode().add(districtName);
                        }
                        if (subdistrictCode != null && !subdistrictCode.isEmpty()) {
                            currentDistrict.getSubdistrictsList().add(subdistrictCode);
                        }
                    }
                }
            }

            if (currentDistrict != null) {
                districtsList.add(currentDistrict);
            }
        }

        return districtsList;
    }

    private static String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }
}

