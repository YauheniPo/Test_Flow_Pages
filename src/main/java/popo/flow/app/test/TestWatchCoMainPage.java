package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import popo.flow.app.pages.MainWatchCo;
import popo.flow.app.pages.items.MenuItem;
import popo.flow.framework.base.BaseTest;

@Log4j2
public class TestWatchCoMainPage extends BaseTest {

    @Test(groups = {"menu"})
    public void testHeaderWomenItem() {
        MenuItem item = MenuItem.TOP_WATCH_BRANDS_MAIN_MENU_ITEM;

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickWomen();

        assertHelper.assertThatTrue(mainWatchCo.menuBar.isItemExist(item),
                String.format("%s does not exist", item.getItem()));
    }

    @Test(groups = {"menu"})
    public void testHeaderMenItem() {
        MenuItem item = MenuItem.TOP_WATCH_BRANDS_MAIN_MENU_ITEM;

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();

        assertHelper.assertThatTrue(mainWatchCo.menuBar.isItemExist(item),
                String.format("%s does not exist", item.getItem()));
    }

    @Test(groups = {"menu"})
    public void testHeaderSaleItem() {
        MenuItem item = MenuItem.ALL;

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickSale();

        assertHelper.assertThatTrue(mainWatchCo.menuBar.isItemExist(item),
                String.format("%s does not exist", item.getItem()));
    }
}
