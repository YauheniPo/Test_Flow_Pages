package popo.flow.app.page;

import lombok.extern.log4j.Log4j2;
import popo.flow.framework.entity.BasePage;

@Log4j2
public class Header extends BasePage {

    public void logout() {
        log.info(String.format("Logout from %s", this.getClass().getSimpleName()));
    }
}
