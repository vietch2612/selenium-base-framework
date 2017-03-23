package me.hoaiviet.webdriver.base.common.core.driverprovider;

import me.hoaiviet.webdriver.base.common.core.BaseWebDriver;
import me.hoaiviet.webdriver.base.common.core.configuration.Configuration;
import me.hoaiviet.webdriver.base.common.core.drivers.Browser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoaiviet on 12/7/16.
 * DriverProvider
 */
public class DriverProvider {

    private static final List<BaseWebDriver> drivers = new ArrayList<>();
    private static int ACTIVE_BROWSER_INDEX = 0;

    @SuppressWarnings("ConstantConditions")
    private static void newInstance() {
        drivers.add(Browser.lookup(Configuration.getBrowser()).getInstance());
    }

    private static BaseWebDriver getBrowserDriver(int index) {
        for (; drivers.size() <= index; ) {
            newInstance();
        }

        return drivers.get(index);
    }

    public static BaseWebDriver getActiveDriver() {
        return getBrowserDriver(ACTIVE_BROWSER_INDEX);
    }

    public static BaseWebDriver switchActiveWindow(int index) {
        ACTIVE_BROWSER_INDEX = index;
        return getActiveDriver();
    }

    public static void close() {
        for (BaseWebDriver webDriver : drivers) {
            if (webDriver != null) {
                try {
                    webDriver.quit();
                } catch (UnsatisfiedLinkError | NoClassDefFoundError e) {
                    e.printStackTrace();
                }
            }
        }
        drivers.clear();
        ACTIVE_BROWSER_INDEX = 0;
    }
}
