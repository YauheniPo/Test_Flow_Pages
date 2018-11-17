package flow_pages.pages;

import flow_pages.BasePage;

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
