package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;
import popo.flow.app.pages.MainWatchCo;
import popo.flow.app.pages.SortingPanel;
import popo.flow.app.pages.WatchBrandPage;
import popo.flow.app.pages.items.SortingItem;
import popo.flow.app.pages.items.WatchItem;
import popo.flow.framework.base.BaseTest;

@Log4j2
public class TestWatchCoBrandSortingPage extends BaseTest {

    @Test(groups = {"sorting", "watch", "menu"})
    public void testSortingWatchBrandItem() {
        final WatchItem watchItem = WatchItem.LUMINOX;
        final SortingItem sortingItem = SortingItem.ANALOG;

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();
        SortingPanel<WatchBrandPage> sortingPanel = mainWatchCo.menuBar.clickPopupMenuItem(watchItem).sortingPanel;
        int count = sortingPanel.getSortingItemsCount(sortingItem);
        WatchBrandPage watchBrandPage = sortingPanel.switchSortingItem(sortingItem).finishSorting();

        assertHelper.assertThatTrue(watchBrandPage.getCountWatches() == count,
                String.format("Sorting count of watches %s does not match %d", watchItem.getWatchBrand(), count));
    }
}
