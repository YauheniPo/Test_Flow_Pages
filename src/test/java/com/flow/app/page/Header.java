package com.flow.app.page;

import com.flow.framework.entity.BasePage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Header extends BasePage {

    public void logout() {
        log.info(String.format("Logout from %s", this.getClass().getSimpleName()));
    }
}
