package com.niveous.snowflake;

import com.niveous.snowflake.snowflake.Snowflake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "niveous.snowflake.start-timestamp=1591876094503",
        "niveous.snowflake.machine-id=25",
        "niveous.snowflake.data-center-id=31"
})
class NiveousSnowflakeApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Test
    void contextLoads() {
        long startTime = System.currentTimeMillis();
        for (int j = 0; j <  (1<<12); j++) {
            System.out.println(snowflake.nextId());
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
