package com.niveous.snowflake.snowflake;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p></p>
 *
 * @author xlp
 * @version 1.0.0
 * @since 2020/6/7 15:17
 */
@Data
@ConfigurationProperties(prefix = "niveous.snowflake")
public class NiveousProperties {

    private long startTimestamp;

    private long machineId;

    private long dataCenterId;
}
