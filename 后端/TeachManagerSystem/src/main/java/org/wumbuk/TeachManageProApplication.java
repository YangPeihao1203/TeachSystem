package org.wumbuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan("org.wumbuk.filter")
@SpringBootApplication
//开启事务管理
@EnableTransactionManagement
public class TeachManageProApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeachManageProApplication.class, args);
    }

}
