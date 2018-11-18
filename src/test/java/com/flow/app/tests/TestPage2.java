package com.flow.app.tests;

import com.flow.app.pages.PageA;
import com.flow.app.pages.PageB;
import com.flow.framework.BaseTest;
import org.testng.annotations.Test;

public class TestPage2 extends BaseTest {

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
