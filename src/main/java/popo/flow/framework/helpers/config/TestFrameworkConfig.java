package popo.flow.framework.helpers.config;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import popo.flow.framework.driver.Browser;

import java.io.IOException;
import java.net.*;

@Log4j2
@Configuration
@ComponentScan("popo.flow.framework")
@ImportResource("classpath:spring.xml")
public class TestFrameworkConfig {

    @Bean
    private static void setLoger() {
        ThreadContext.put("threadContext", "");
    }

    @Bean
    private static Boolean netIsAvailable() {
        try {
            final URL url = new URL(Browser.getBROWSER_URL());
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            log.info("Network connection is available.");
            return true;
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.info("Network connection does not available.");
            return false;
        }
    }
}
