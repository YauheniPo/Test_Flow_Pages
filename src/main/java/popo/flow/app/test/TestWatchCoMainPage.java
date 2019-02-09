package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import popo.flow.app.page.MainWatchCo;
import popo.flow.framework.entity.BaseTest;

@Log4j2
public class TestWatchCoMainPage extends BaseTest {

    @Test(groups = {"page"})
    public void testHeaderItems() {
        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();
        mainWatchCo.menuBar.clickWomen();
    }

    @Test(groups = {"page"})
    public void testHeaderItems2() {
        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickWomen();
        mainWatchCo.menuBar.clickMen();
    }
}
