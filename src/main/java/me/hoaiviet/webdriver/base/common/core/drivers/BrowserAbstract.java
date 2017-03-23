package me.hoaiviet.webdriver.base.common.core.drivers;

import me.hoaiviet.webdriver.base.common.core.configuration.Configuration;
import me.hoaiviet.webdriver.base.common.core.BaseWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by hoaiviet on 12/6/16.
 * BrowserAbstract
 */
public abstract class BrowserAbstract {

    protected final DesiredCapabilities capabilities = new DesiredCapabilities();

    /**
     * Get a ready to models instance for chosen browser
     *
     * @return driver
     */
    BaseWebDriver getInstance() {
        setOptions();
        setExtensions();
        setBrowserLogging(Level.SEVERE);
        BaseWebDriver driver = create();
        setTimeouts(driver);
        setListener(driver);

        return driver;
    }

    /**
     * Set Browser specific options, before creating a working instance
     */
    protected abstract void setOptions();

    /**
     * Create a working instance of a Browser
     *
     * @return Driver
     */
    protected abstract BaseWebDriver create();

    private void setBrowserLogging(Level logLevel) {
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, logLevel);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
    }

    private void setTimeouts(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void setListener(BaseWebDriver webDriver) {
        //TODO: Set Listener
    }

    /**
     * Add browser extensions
     * <p>
     * params extensionName
     */
    protected abstract void addExtension(String extensionName);

    private void setExtensions() {
        for (String name : Configuration.getExtension()) {
            addExtension(name);
        }
    }
}
