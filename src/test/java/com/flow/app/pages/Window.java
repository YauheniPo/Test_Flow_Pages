package com.flow.app.pages;

import com.flow.framework.BasePage;

public class Window<R extends BasePage> extends BasePage {

    private R page;

    public Window(R page) {
        this.page = page;
    }

    public Window methodWindow() {
        System.out.println("method Window");
        return this;
    }

    public R methodWindowToPage() {
        System.out.println(String.format("method Window to %s", this.page.getClass().getSimpleName()));
        return this.page;
    }
}
