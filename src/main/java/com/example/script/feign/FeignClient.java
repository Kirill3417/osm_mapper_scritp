package com.example.script.feign;

import com.example.script.request.IndicatorRequest;
import com.example.script.request.ObjectRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@org.springframework.cloud.openfeign.FeignClient(name = "indicator", url = "${ocm.url}", configuration = FeignConfig.class)
public interface FeignClient {

    @PostMapping(value = "indicators/find/", consumes = "application/json")
    Map<String, Object> indicatorsFind(@RequestBody IndicatorRequest indicatorRequest);

    @PostMapping(value = "geoobjects/find/", consumes = "application/json")
    Map<String, Object> geoobjectsFind(@RequestBody ObjectRequest objectRequest);
}
