package com.flow.framework;

import com.flow.app.pages.PageA;
import com.flow.app.pages.PageB;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BasePage extends BaseEntity {

    public PageA getPageA() {
        log.info("get PageA");
        return new PageA();
    }

    public PageB getPageB() {
        log.info("get PageB");
        return new PageB();
    }
}
