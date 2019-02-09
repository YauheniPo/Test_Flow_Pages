package popo.flow.framework.config;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import popo.flow.framework.driver.Browser;

@Log4j2
@Configuration
@ComponentScan("popo.flow.framework")
@ImportResource("classpath:spring.xml")
public class TestFrameworkConfig {

    @Bean
    public RemoteWebDriver webDriver() {
        return Browser.getDriver();
    }
}
