package com.flow.app.pages;

import com.flow.framework.BasePage;

public class Header extends BasePage {

    public void logout() {
        System.out.println(String.format("Logout from %s", this.getClass().getSimpleName()));
    }
}
