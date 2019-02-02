package popo.flow.framework.entity;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import popo.flow.framework.AssertHelper;

import java.lang.reflect.Method;
import java.util.Arrays;

@Log4j2
public class BaseTest extends BaseEntity {

    protected AssertHelper assertHelper = new AssertHelper();

    @Parameters({"name"})
    @BeforeMethod
    public void beforeMethod(Method m, @Optional(value = "YauheniPo") String name) {
        System.out.println(String.format("---------------- %s ----------------", name));
        log.info("Test class: " + m.getDeclaringClass().getName());
        Test t = m.getAnnotation(Test.class);
        log.info(String.format("Thread - %d", Thread.currentThread().getId()));
        log.info(getWebDriver().getCapabilities().getBrowserName());
        log.info("Groups: " + Arrays.toString(t.groups()));
    }
}
