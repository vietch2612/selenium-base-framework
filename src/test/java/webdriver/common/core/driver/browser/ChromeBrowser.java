package webdriver.common.core.driver.browser;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import webdriver.common.core.HVWebDriver;
import webdriver.common.core.driver.BrowserAbstract;
import webdriver.common.core.helper.ExtensionHelper;

import java.io.File;

/**
 * Created by hoaiviet on 5/18/17.
 * ChromeBrowser
 */
public class ChromeBrowser extends BrowserAbstract {

    private static final String CHROME_DIVER_PATH_FORMAT = "ChromeDriver/chromedriver_%s";
    private static final String CHROME_DRIVER_PATH_MAC = String.format(CHROME_DIVER_PATH_FORMAT, "mac64");
    private static final String CHROME_DRIVER_PATH_WINDOWS = String.format(CHROME_DIVER_PATH_FORMAT, "win32");

    private final ChromeOptions chromeOptions = new ChromeOptions();

    @Override
    public void setOptions() {
        String chromeBinaryPath;
        String osName = System.getProperty("os.name").toUpperCase();

        if (osName.contains("WINDOWS")) {
            chromeBinaryPath = CHROME_DRIVER_PATH_WINDOWS;
        } else {
            chromeBinaryPath = CHROME_DRIVER_PATH_MAC;
        }

        File chromeDriver = new File(ClassLoader.getSystemResource(chromeBinaryPath).getPath());

        chromeDriver.setExecutable(true);

        System.setProperty("webdriver.chrome.driver", chromeDriver.getPath());

        chromeOptions.addArguments("start-maximized");
    }

    @Override
    public HVWebDriver create() {
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new HVWebDriver(new ChromeDriver(capabilities));
    }

    @Override
    public void addExtension(String extensionName) {
        chromeOptions.addExtensions(ExtensionHelper.findExtension(extensionName, "crx"));
    }
}
