package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import popo.flow.app.page.PageA;
import popo.flow.app.page.PageB;
import popo.flow.framework.entity.BaseTest;

@Log4j2
public class TestPage extends BaseTest {

    @Test(groups = {"page", "TestPage"})
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
