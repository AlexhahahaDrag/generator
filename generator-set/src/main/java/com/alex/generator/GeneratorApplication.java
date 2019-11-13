package com.alex.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Description: 启动spring boot
 * @Author:     alex
 * @CreateDate: 2019/11/13 16:16
 * @Version:    1.0
 *
*/
@SpringBootApplication(scanBasePackages = "com.alex")
public class GeneratorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GeneratorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }

}
