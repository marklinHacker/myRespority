package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * description: LyGateway <br>
 * date: 2019/8/23 19:29 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@SpringBootApplication
@EnableZuulProxy
public class LyGateway {
    public static void main(String[] args) {
        SpringApplication.run(LyGateway.class, args);
    }
}
