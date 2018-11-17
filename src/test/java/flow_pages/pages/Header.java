package flow_pages.pages;

import flow_pages.BasePage;

public class Header extends BasePage {

    public void logout() {
        System.out.println(String.format("Logout from %s", this.getClass().getSimpleName()));
    }
}
