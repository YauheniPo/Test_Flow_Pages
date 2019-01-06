package com.flow.framework;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;

@Log4j2
public class AssertHelper {

    public void assertThatTrue(boolean condition, String msg) {
        log.info(String.format("assertTrue(%s, %s)", condition, msg));
        Assert.assertTrue(condition, msg);
    }
}
