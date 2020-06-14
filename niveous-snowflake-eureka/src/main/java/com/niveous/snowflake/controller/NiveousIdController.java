package com.niveous.snowflake.controller;

import com.niveous.snowflake.snowflake.Snowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author xlp
 * @version 1.0.0
 * @since 2020/6/11 19:38
 */
@RestController
@RequestMapping("/niveous/snowflake")
public class NiveousIdController {

    @Autowired
    private Snowflake snowflake;

    @GetMapping
    public long getNextId(){
        return snowflake.nextId();
    }

}
