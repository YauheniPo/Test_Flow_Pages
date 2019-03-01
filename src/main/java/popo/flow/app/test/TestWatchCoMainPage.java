package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import popo.flow.app.pages.MainWatchCo;
import popo.flow.framework.base.BaseTest;

@Log4j2
public class TestWatchCoMainPage extends BaseTest {

    private static final String TOP_WATCH_BRANDS_MAIN_MENU_ITEM = "Top Watch Brands";

    @Test(groups = {"pages"})
    public void testHeaderWomenItem() {
        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickWomen();

        assertHelper.assertThatTrue(mainWatchCo.menuBar.isElementExist(TOP_WATCH_BRANDS_MAIN_MENU_ITEM),
                String.format("%s does not exist", TOP_WATCH_BRANDS_MAIN_MENU_ITEM));
    }

    @Test(groups = {"pages"})
    public void testHeaderMenItem() {
        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();

        assertHelper.assertThatTrue(mainWatchCo.menuBar.isElementExist(TOP_WATCH_BRANDS_MAIN_MENU_ITEM),
                String.format("%s does not exist", TOP_WATCH_BRANDS_MAIN_MENU_ITEM));
    }

    @Test(groups = {"pages"})
    public void testHeaderSaleItem() {
        String allMenuBarItem = "ALL";

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickSale();

        assertHelper.assertThatTrue(mainWatchCo.menuBar.isElementExist(allMenuBarItem),
                String.format("%s does not exist", allMenuBarItem));
    }
}
