package com.flow.app.page;

import com.flow.framework.entity.BasePage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PageA extends BasePage {

    public Header header = new Header();

    public PageA methodPageA() {
        log.info("method PageA");
        return this;
    }

    public Window<PageA> methodPageAtoWindow() {
        log.info("method PageA to Window");
        return new Window<>(this);
    }

    public String getPageAString() {
        return "PageA";
    }
}
