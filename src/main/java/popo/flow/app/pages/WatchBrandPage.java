package popo.flow.app.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import popo.flow.framework.base.BasePage;
import popo.flow.framework.helpers.Locators;

import static com.codeborne.selenide.Selenide.$;

public class WatchBrandPage extends BasePage {

    public final SortingPanel<WatchBrandPage> sortingPanel = new SortingPanel<>(this);
    private SelenideElement productsPanel = $(Locators.getByPartialClassTextOfElement(
            Locators.LocatorElement.DIV, Locators.getString("watch.items"))).shouldBe(Condition.exist);

    public int getCountWatches() {
        return productsPanel.findAll(Locators.getByXpath(".//li")).size();
    }
}