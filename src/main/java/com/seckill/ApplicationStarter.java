package com.seckill;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author boting.guo
 * @date 2020/11/10 15:53
 */

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
        log.info("----- Application running -----");
    }
}
