package popo.flow.app.test;

import lombok.extern.log4j.Log4j2;
import static org.mockito.Mockito.*;

import org.openqa.selenium.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import popo.flow.app.pages.MainWatchCo;
import popo.flow.app.pages.SortingPanel;
import popo.flow.app.pages.WatchBrandPage;
import popo.flow.app.pages.items.SortingItem;
import popo.flow.app.pages.items.WatchItem;
import popo.flow.framework.base.BaseTest;


@Log4j2
public class TestWatchCoBrandSortingPage extends BaseTest {

    WebDriver mockDriver;
    WebElement mockElement;

    @BeforeMethod(groups = "mock")
    public void setupForMock() {
        this.mockDriver = mock(WebDriver.class, withSettings().extraInterfaces(JavascriptExecutor.class));
        this.mockElement = mock(WebElement.class, withSettings().name("elementName"));
        when(this.mockDriver.findElement(By.id("testmock"))).thenReturn(mockElement);
        when(this.mockElement.getAttribute("name")).thenReturn("elementName");
    }

    @Test(groups = {"sorting", "watch", "menu"})
    public void testSortingWatchBrandItem() {
        final WatchItem watchItem = WatchItem.LUMINOX;
        final SortingItem sortingItem = SortingItem.ANALOG;

        MainWatchCo mainWatchCo = new MainWatchCo();
        mainWatchCo.menuBar.clickMen();
        SortingPanel<WatchBrandPage> sortingPanel = mainWatchCo.menuBar.clickPopupMenuItem(watchItem).sortingPanel;
        int count = sortingPanel.getSortingItemsCount(sortingItem);
        WatchBrandPage watchBrandPage = sortingPanel.switchSortingItem(sortingItem).finishSorting();
        int actualCount = watchBrandPage.getCountWatches();

        assertHelper.assertThatTrue(actualCount == count,
                String.format("Sorting count of watches %s does not match %d, exists %d watch(es)", watchItem.getWatchBrand(), count, actualCount));
    }

    @Test(groups = {"sorting", "watch", "menu", "mock"})
    public void testSortingPanelMockItem() {
        assertHelper.assertThatTrue(getName("testmock").equals("elementName"));
    }

    private String getName(String id){
        WebElement testElement = mockDriver.findElement(By.id(id));
        return testElement.getAttribute("name");
    }
}
