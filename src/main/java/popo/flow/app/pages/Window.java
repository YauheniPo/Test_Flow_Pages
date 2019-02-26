package popo.flow.app.pages;

import lombok.extern.log4j.Log4j2;
import popo.flow.framework.base.BasePage;

@Log4j2
public class Window<R extends BasePage> extends BasePage {

    private R page;

    public Window(R page) {
        this.page = page;
    }

    public Window methodWindow() {
        log.info("method Window");
        return this;
    }

    public R methodWindowToPage() {
        log.info(String.format("method Window to %s", this.page.getClass().getSimpleName()));
        return this.page;
    }
}
