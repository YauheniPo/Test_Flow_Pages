package popo.flow.app.page;

import lombok.extern.log4j.Log4j2;
import popo.flow.framework.entity.BasePage;

@Log4j2
public class PageB extends BasePage {

    public Header header = new Header();

    public PageB methodPageB() {
        log.info("method PageB");
        return this;
    }

    public Window<PageB> methodPageBToWindow() {
        log.info("method PageB to Window");
        return new Window<>(this);
    }

    public String getPageBString() {
        return "PageB";
    }
}
