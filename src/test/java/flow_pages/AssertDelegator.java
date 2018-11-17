package flow_pages;

import org.testng.Assert;

public class AssertDelegator {

    public void assertThatTrue(boolean condition, String msg) {
        System.out.println(String.format("assertTrue(%s, %s)", condition, msg));
        Assert.assertTrue(condition, msg);
    }
}
