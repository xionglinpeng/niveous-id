package com.niveous.snowflake;

import com.niveous.snowflake.snowflake.Snowflake;
import org.junit.jupiter.api.Test;

/**
 * <p></p>
 *
 * @author xlp
 * @version 1.0.0
 * @since 2020/6/7 16:32
 */
public class SnowflakeTest {

    @Test
    public void snowflake()  {
        Snowflake snowflake = new Snowflake(System.currentTimeMillis(), 31, 31);
        long startTime = System.currentTimeMillis();
        for (int j = 0; j <  (1<<12); j++) {
            System.out.println(snowflake.nextId());
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
