package com.devin.ciserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by devin on 2018/8/8
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
@ComponentScan("com.devin.ciserver")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}