package webdriver.common.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by hoaiviet on 5/18/17.
 * HVWebDriver
 */
public class HVWebDriver extends EventFiringWebDriver{

    public HVWebDriver(WebDriver driver) {
        super(driver);
    }
}
