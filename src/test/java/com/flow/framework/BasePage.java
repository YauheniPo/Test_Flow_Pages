package com.flow.framework;

import com.flow.app.pages.PageA;
import com.flow.app.pages.PageB;

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
