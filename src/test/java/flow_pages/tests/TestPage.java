package flow_pages.tests;

import flow_pages.BaseTest;
import flow_pages.pages.PageA;
import flow_pages.pages.PageB;
import org.testng.annotations.Test;

public class TestPage extends BaseTest {

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
