package flow_pages.pages;

import flow_pages.BasePage;

public class PageB extends BasePage {

    public Header header = new Header();

    public PageB methodPageB() {
        System.out.println("method PageB");
        return this;
    }

    public Window<PageB> methodPageBtoWindow() {
        System.out.println("method PageB to Window");
        return new Window<>(this);
    }

    public String getPageBString() {
        return "PageB";
    }
}
