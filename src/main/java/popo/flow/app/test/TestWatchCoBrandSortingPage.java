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
        final WatchItem luminox = WatchItem.LUMINOX;
        final SortingItem analog = SortingItem.ANALOG;

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();
        SortingPanel<WatchBrandPage> sortingPanel = mainWatchCo.menuBar.clickPopupMenuItem(luminox).sortingPanel;
        int count = sortingPanel.getSortingItemsCount(analog);
        WatchBrandPage watchBrandPage = sortingPanel.switchSortingItem(analog).finishSorting();
        int actualCount = watchBrandPage.getCountWatches();

        assertHelper.assertThatTrue(actualCount == count,
                String.format("Sorting count of watches %s does not match %d, exists %d watch(es)", luminox.getWatchBrand(), count, actualCount));
    }
}
