package popo.flow.framework.base;

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
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "popo.flow.framework.base.BaseEntity.beforeMethod")
    public void beforeMethod(Method m, @Optional(value = "YauheniPo") String name) {
        log.info(String.format("---- %s ---- %s ---- %s ---- Thread - %d ----", name, m.getDeclaringClass().getName(), m.getName(), Thread.currentThread().getId()));
        Test t = m.getAnnotation(Test.class);
        log.info("Groups: " + Arrays.toString(t.groups()));
    }
}
