package popo.flow.framework.config;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import popo.flow.framework.driver.DriverManager;

@Log4j2
@Configuration
@ComponentScan("popo.flow.framework")
@ImportResource("classpath:spring.xml")
public class TestFrameworkConfig {

    @Bean(name = "driver")
    public WebDriver webDriver() {
        return DriverManager.getDriver();
    }
}
