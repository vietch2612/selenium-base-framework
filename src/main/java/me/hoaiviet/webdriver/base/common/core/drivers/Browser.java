package me.hoaiviet.webdriver.base.common.core.drivers;

import me.hoaiviet.webdriver.base.common.core.BaseWebDriver;
import me.hoaiviet.webdriver.base.common.core.drivers.browsers.ChromeBrowser;
import me.hoaiviet.webdriver.base.common.core.drivers.browsers.EdgeBrowser;

/**
 * Created by hoaiviet on 12/6/16.
 * Browser
 */
public enum Browser {

    CHROME(ChromeBrowser.class, "CHROME"),
    EDGE(EdgeBrowser.class, "EDGE");

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

    public BaseWebDriver getInstance() {
        try {
            return browserClass.newInstance().getInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<? extends BrowserAbstract> getBrowserClass() {
        return browserClass;
    }
}
