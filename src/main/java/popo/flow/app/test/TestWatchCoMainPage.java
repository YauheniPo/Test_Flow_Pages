package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import popo.flow.app.pages.MainWatchCo;
import popo.flow.framework.base.BaseTest;

@Log4j2
public class TestWatchCoMainPage extends BaseTest {

    @Test(groups = {"pages"})
    public void testHeaderItems() {
        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();
        mainWatchCo.menuBar.clickWomen();
    }

    @Test(groups = {"pages"})
    public void testHeaderItems2() {
        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickWomen();
        mainWatchCo.menuBar.clickMen();
    }
}
