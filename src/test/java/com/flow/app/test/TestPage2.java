package com.flow.app.test;

import com.flow.app.page.PageA;
import com.flow.app.page.PageB;
import com.flow.framework.entity.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

@Log4j2
public class TestPage2 extends BaseTest {

    @Test(groups = {"page", "TestPage2"})
    public void test() {
        PageA pageA = new PageA()
                .methodPageA()
                .methodPageAtoWindow()
                .methodWindow()
                .methodWindowToPage()
                .getPageB()
                .methodPageB()
                .methodPageBToWindow()
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
