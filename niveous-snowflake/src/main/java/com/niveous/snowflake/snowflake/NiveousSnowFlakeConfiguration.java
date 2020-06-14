package com.niveous.snowflake.snowflake;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author xlp
 * @version 1.0.0
 * @since 2020/6/11 19:40
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(NiveousProperties.class)
public class NiveousSnowFlakeConfiguration {

    @Bean
    public Snowflake snowflake(NiveousProperties niveousProperties){
        log.info("Snowflake configure => startTimestamp:{},machineId:{},dataCenterId:{}",
                niveousProperties.getStartTimestamp(),
                niveousProperties.getMachineId(),
                niveousProperties.getDataCenterId());
        return new Snowflake(
                niveousProperties.getStartTimestamp(),
                niveousProperties.getMachineId(),
                niveousProperties.getDataCenterId());
    }
}
