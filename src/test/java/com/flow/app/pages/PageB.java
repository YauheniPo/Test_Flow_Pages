package com.flow.app.pages;

import com.flow.framework.BasePage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PageB extends BasePage {

    public Header header = new Header();

    public PageB methodPageB() {
        log.info("method PageB");
        return this;
    }

    public Window<PageB> methodPageBtoWindow() {
        log.info("method PageB to Window");
        return new Window<>(this);
    }

    public String getPageBString() {
        return "PageB";
    }
}
