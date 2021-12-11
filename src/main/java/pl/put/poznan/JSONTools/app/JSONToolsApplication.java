package pl.put.poznan.JSONTools.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.JSONTools.rest"})
public class JSONToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JSONToolsApplication.class, args);
    }
}
