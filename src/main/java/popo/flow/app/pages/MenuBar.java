package popo.flow.app.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import popo.flow.app.pages.items.MenuItem;
import popo.flow.app.pages.items.WatchItem;
import popo.flow.framework.base.BasePage;
import popo.flow.framework.helpers.Locators;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MenuBar extends BasePage {

    private SelenideElement
            menuBar = $(Locators.get("menubar")).shouldBe(Condition.exist),
            saleElement = menuBar.$(Locators.get("menubar.sale")),
            menElement = menuBar.$(Locators.get("menubar.men")),
            womenElement = menuBar.$(Locators.get("menubar.women"));

    public void clickMen() {
        log.info("click Men Item");
        menElement.click();
    }

    public void clickWomen() {
        log.info("click Women Item");
        womenElement.click();
    }

    public void clickSale() {
        log.info("click Sale Item");
        saleElement.click();
    }

    public boolean isItemExist(MenuItem item) {
        return $(Locators.getByText(item.getItem())).exists();
    }

    public WatchBrandPage clickPopupMenuItem(WatchItem item) {
        $(Locators.getWithText(item.getWatchBrand())).shouldBe(Condition.enabled).click();
        return new WatchBrandPage();
    }
}
