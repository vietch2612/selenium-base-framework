package me.hoaiviet.webdriver.base.common.core.drivers.browsers;

import me.hoaiviet.webdriver.base.common.core.BaseWebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import me.hoaiviet.webdriver.base.common.core.drivers.BrowserAbstract;

import java.io.File;

/**
 * Created by hoaiviet on 1/14/17.
 * Microsoft Edge browser
 */
public class EdgeBrowser extends BrowserAbstract {

    private static final String EDGE_DIVER_PATH = "EdgeDriver/MicrosoftWebDriver.exe";

    private final EdgeOptions edgeOptions = new EdgeOptions();

    @Override
    public void setOptions() {
        File edgeDriver = new File(ClassLoader.getSystemResource(EDGE_DIVER_PATH).getPath());
        //noinspection ResultOfMethodCallIgnored
        edgeDriver.setExecutable(true);

        System.setProperty("webdriver.edge.driver", edgeDriver.getPath());
    }

    @Override
    public BaseWebDriver create() {
        capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
        return new BaseWebDriver(new EdgeDriver(capabilities));
    }

    @Override
    public void addExtension(String extensionName) {
        //No extension for Edge Driver
    }
}
