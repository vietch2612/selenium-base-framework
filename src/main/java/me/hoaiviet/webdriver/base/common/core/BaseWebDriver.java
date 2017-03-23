package me.hoaiviet.webdriver.base.common.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by hoaiviet on 12/6/16.
 * BaseWebDriver
 */
public class BaseWebDriver extends EventFiringWebDriver {

    public BaseWebDriver(WebDriver driver) {
        super(driver);
    }
}
