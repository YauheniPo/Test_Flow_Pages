package com.flow.framework.entity;

import com.flow.framework.AssertDelegator;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

@Log4j2
public class BaseTest extends BaseEntity {

    protected AssertDelegator assertHelper = new AssertDelegator();

    @BeforeMethod
    public void beforeMethod(Method m) {
        log.info("Test class: " + m.getDeclaringClass().getName());
        Test t = m.getAnnotation(Test.class);
        log.info("Groups: " + Arrays.toString(t.groups()));
    }
}
