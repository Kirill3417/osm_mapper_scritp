package com.example.script.service;

import com.example.script.feign.FeignClient;
import com.example.script.mapper.ExelMapper;
import com.example.script.models.*;
import com.example.script.request.IndicatorRequest;
import com.example.script.request.ObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service

public class StartService {

    @Autowired
    private ExelMapper exelMapper;

    @Autowired
    private FeignClient feignClient;

    @Value("${filePath.constructionIndicators}")
    String constructionIndicatorsPath;

    @Value("${filePath.districtsAndSubdistricts}")
    String districtsAndSubdistrictsPath;

    private final static String SEPARATOR_LINE = "\n\n---------------------------------------------------------------------\n\n";

    ObjectMapper objectMapper = new ObjectMapper();


    public void createConstructionIndicators() throws IOException {
        List<EOModel> eoModelList = exelMapper.mapEO(constructionIndicatorsPath);
//        List<UBIKModel> ubikModelList = exelMapper.mapUBIK(constructionIndicatorsPath);
        String userHome = System.getProperty("user.home");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));


        int fileCount = 1;
        int requestCount = 0;
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(userHome + "/Desktop/constructionIndicatorsResponse_" + fileCount + ".docx");

        try {
            for (EOModel eoModel : eoModelList) {
                IndicatorRequest indicatorRequest = new IndicatorRequest(Collections.singletonList(eoModel.getEntityCode()), new DateInterval());
                Map<String, Object> indicatorResponse = null;
                String errorMessage = null;

                try {
                    indicatorResponse = feignClient.indicatorsFind(indicatorRequest);
                } catch (Exception e) {
                    errorMessage = eoModel.getEntityCode() + " - " + e.getMessage();
                }

                String requestJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(indicatorRequest);
                XWPFParagraph requestParagraph = document.createParagraph();
                requestParagraph.createRun().setText("Запрос: " + requestJson + " - " + eoModel.getName());

                if (indicatorResponse != null) {
                    String responseJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(indicatorResponse);
                    XWPFParagraph responseParagraph = document.createParagraph();
                    responseParagraph.createRun().setText("Ответ: " + responseJson + SEPARATOR_LINE);
                } else {
                    XWPFParagraph errorParagraph = document.createParagraph();
                    errorParagraph.createRun().setText("Ошибка в момент выполнения запроса: " + errorMessage);
                }

                requestCount++;
                System.out.println(requestCount);

                if (requestCount % 10 == 0) {
                    document.write(out);
                    out.close();
                    fileCount++;
                    document = new XWPFDocument();
                    out = new FileOutputStream(userHome + "/Desktop/constructionIndicatorsResponse_" + fileCount + ".docx");
                }
            }

            if (requestCount % 10 != 0) {
                document.write(out);
            }
        } finally {
            out.close();
        }
    }

    public void createDistrictsAndSubdistricts() throws IOException {
        List<DistrictsAndSubdistrictsModel> districtsAndSubdistrictsModels = exelMapper.mapDistrictsAndSubdistricts(districtsAndSubdistrictsPath);
        String userHome = System.getProperty("user.home");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));

        int fileCount = 1;

        for (DistrictsAndSubdistrictsModel districtsAndSubdistrictsModel : districtsAndSubdistrictsModels) {
            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(userHome + "/Desktop/districtsAndSubdistricts_" + fileCount + ".docx");
            Map<String, Object> geoobjectsResponse = null;
            String errorMessage = null;

            try {
                geoobjectsResponse = feignClient.geoobjectsFind(
                        new ObjectRequest(Collections.singletonList(districtsAndSubdistrictsModel.getRegionCode()), districtsAndSubdistrictsModel.getSubdistrictsList()));
            } catch (Exception e) {
                errorMessage = districtsAndSubdistrictsModel.getRegionCode() + " - " + e.getMessage();
            }

            String requestJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    new ObjectRequest(Collections.singletonList(districtsAndSubdistrictsModel.getRegionCode()), districtsAndSubdistrictsModel.getSubdistrictsList()));
            XWPFParagraph requestParagraph = document.createParagraph();
            requestParagraph.createRun().setText("Запрос: " + requestJson);

            if (geoobjectsResponse != null) {
                String responseJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(geoobjectsResponse);
                XWPFParagraph responseParagraph = document.createParagraph();
                responseParagraph.createRun().setText("Ответ: " + responseJson + SEPARATOR_LINE);
            } else {
                XWPFParagraph errorParagraph = document.createParagraph();
                errorParagraph.createRun().setText("Ошибка: " + errorMessage + SEPARATOR_LINE);
            }

            document.write(out);
            out.close();
            System.out.println(fileCount);
            fileCount++;
        }
    }
}
