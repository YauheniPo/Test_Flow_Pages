package popo.flow.framework.entity;

import lombok.extern.log4j.Log4j2;
import popo.flow.app.page.PageA;
import popo.flow.app.page.PageB;

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
