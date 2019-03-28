package popo.flow.app.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import popo.flow.app.pages.items.SortingItem;
import popo.flow.framework.base.BasePage;
import popo.flow.framework.helpers.Locators;
import popo.flow.framework.helpers.Waiters;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static popo.flow.app.Constants.REG_EXP_NUMBER_BETWEEN_BRACKETS;

@Log4j2
@RequiredArgsConstructor
public class SortingPanel<R extends WatchBrandPage> extends BasePage {

    private final SelenideElement sortingSidebar = $(Locators.get("watch.sort")).waitUntil(exist, Waiters.EXIST_TIMEOUT);
    @NonNull private R watchBrandPage;

    public SortingPanel switchSortingItem(SortingItem item) {
        sortingSidebar.find(Locators.getWithText(item.getSortingItem())).click();
        $(Locators.get("watch.sortItems")).waitUntil(text(item.getSortingItem()), Waiters.EXIST_TIMEOUT);
        return this;
    }

    public int getSortingItemsCount(SortingItem item) {
        String count = sortingSidebar.find(Locators.getWithText(item.getSortingItem())).parent().getText();
        return Integer.parseInt(count.replaceAll(REG_EXP_NUMBER_BETWEEN_BRACKETS, ""));
    }

    public R finishSorting() {
        return this.watchBrandPage;
    }
}
