package popo.flow.app.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import popo.flow.framework.base.BasePage;
import popo.flow.framework.helpers.Locators;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MenuBar extends BasePage {

    private SelenideElement menu = $(Locators.get("menubar")).shouldBe(Condition.exist),
            menElement = menu.$(Locators.get("menubar.men")),
            womenElement = menu.$(Locators.get("menubar.women"));

    public void clickMen() {
        log.info("click Men Item");
        menElement.click();
    }

    public void clickWomen() {
        log.info("click Women Item");
        womenElement.click();
    }
}
