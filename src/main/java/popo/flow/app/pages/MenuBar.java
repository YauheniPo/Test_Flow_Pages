package popo.flow.app.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import popo.flow.framework.base.BasePage;
import popo.flow.framework.helpers.Locators;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MenuBar extends BasePage {

    private static final SelenideElement
            MENU = $(Locators.get("menubar")).shouldBe(Condition.exist),
            MEN_ELEMENT = MENU.$(Locators.get("menubar.men")),
            WOMEN_ELEMENT = MENU.$(Locators.get("menubar.women"));

    public void clickMen() {
        log.info("click Men Item");
        MEN_ELEMENT.click();
    }

    public void clickWomen() {
        log.info("click Women Item");
        WOMEN_ELEMENT.click();
    }
}
