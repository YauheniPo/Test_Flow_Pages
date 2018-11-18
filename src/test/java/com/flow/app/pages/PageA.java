package com.flow.app.pages;

import com.flow.framework.BasePage;

public class PageA extends BasePage {

    public Header header = new Header();

    public PageA methodPageA() {
        System.out.println("method PageA");
        return this;
    }

    public Window<PageA> methodPageAtoWindow() {
        System.out.println("method PageA to Window");
        return new Window<>(this);
    }

    public String getPageAString() {
        return "PageA";
    }
}
