package popo.flow.app.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import popo.flow.framework.entity.BasePage;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class MenuBar extends BasePage {

    private SelenideElement menu = $(byClassName("menu")).shouldBe(Condition.exist),
            menElement = menu.$(byText("Men")),
            womenElement = menu.$(byText("Women"));

    public void clickMen() {
        log.info("click Men Item");
        menElement.click();
    }

    public void clickWomen() {
        log.info("click Women Item");
        womenElement.click();
    }
}
