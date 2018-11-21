package com.flow.app.tests;

import com.flow.app.pages.PageA;
import com.flow.app.pages.PageB;
import com.flow.framework.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class TestPage2 extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        log.info(this.getClass().getName());
    }

    @Test
    public void test() {
        PageA pageA = new PageA()
                .methodPageA()
                .methodPageAtoWindow()
                .methodWindow()
                .methodWindowToPage()
                .getPageB()
                .methodPageB()
                .methodPageBtoWindow()
                .methodWindow()
                .methodWindowToPage()
                .getPageA();

        String pageAString = pageA.getPageAString();

        assertHelper.assertThatTrue(pageAString.contains("Page"), "message");

        PageB pageB = pageA
                .methodPageA()
                .getPageB();

        String pageBString = pageB
                .methodPageB()
                .getPageBString();

        assertHelper.assertThatTrue(pageBString.contains("Page"), "message");

        pageB.header.logout();
    }
}
