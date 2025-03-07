package com.example.script;

import com.example.script.feign.FeignClient;
import com.example.script.mapper.ExelMapper;
import com.example.script.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ScriptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScriptApplication.class, args);
    }


}
