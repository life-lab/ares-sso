package com.xxl.sso.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author xuxueli 2018-03-22 23:41:47
 */
@SpringBootApplication
@Slf4j
public class XxlSsoServerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = null;
        boolean success = true;
        try {
            applicationContext = SpringApplication.run(XxlSsoServerApplication.class, args);
        } catch (Exception e) {
            success = false;
            log.info("*****************************启动失败*******************************", e);
        }
        if (success) {
            log.info("*****************************启动成功*******************************");
        }
    }
}