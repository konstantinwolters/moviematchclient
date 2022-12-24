package com.example.moviematchweb.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.moviematchweb.proxy")
public class ProjectConfig {

}
