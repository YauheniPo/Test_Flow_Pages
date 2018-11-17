package flow_pages;

import flow_pages.pages.PageA;
import flow_pages.pages.PageB;

public class BasePage extends BaseEntity {

    public PageA getPageA() {
        System.out.println("get PageA");
        return new PageA();
    }

    public PageB getPageB() {
        System.out.println("get PageB");
        return new PageB();
    }
}
