package webdriver.common.core.driver;

import webdriver.common.core.HVWebDriver;
import webdriver.common.core.driver.browser.ChromeBrowser;

/**
 * Created by hoaiviet on 5/18/17.
 * Browser
 */
public enum Browser {

    CHROME(ChromeBrowser.class, "CHROME");

    private final Class<? extends BrowserAbstract> browserClass;
    private final String name;

    Browser(Class<? extends BrowserAbstract> browserClass, String name) {
        this.name = name;
        this.browserClass = browserClass;
    }

    public static Browser lookup(String browserName) {
        for (Browser name : Browser.values()) {
            if (name.getName().equalsIgnoreCase(browserName)) {
                return name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public HVWebDriver getInstance() {
        try {
            return browserClass.newInstance().getInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<? extends BrowserAbstract> getBrowserClass() {
        return browserClass;
    }
}
