package com.niveous.snowflake;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class NiveousSnowflakeEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiveousSnowflakeEurekaApplication.class, args);
    }

}
