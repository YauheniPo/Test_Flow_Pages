package popo.flow.app.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import popo.flow.framework.base.BasePage;
import popo.flow.framework.helpers.Locators;

import static com.codeborne.selenide.Selenide.$;

public class WatchBrandPage extends BasePage {

    public final SortingPanel<WatchBrandPage> sortingPanel = new SortingPanel<>(this);
    private SelenideElement productsPanel = $(Locators.get("brand.items")).shouldBe(Condition.visible);

    public int getCountItems() {
        return productsPanel.findAll(Locators.getByXpath(".//li")).size();
    }
}
