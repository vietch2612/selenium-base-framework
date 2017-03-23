package me.hoaiviet.webdriver.base.common.templates;

import me.hoaiviet.webdriver.base.common.core.BaseWebDriver;
import me.hoaiviet.webdriver.base.common.core.configuration.Configuration;
import me.hoaiviet.webdriver.base.common.core.driverprovider.DriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;

/**
 * Created by hoaiviet on 12/7/16.
 * Core Test Template
 */
public abstract class CoreTestTemplate {

    protected BaseWebDriver driver;

    private void refreshDriver() {
        driver = DriverProvider.getActiveDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
    }

    @BeforeClass(alwaysRun = true)
    public void initTest() {
        me.hoaiviet.webdriver.base.common.core.configuration.Configuration.clearCustomTestProperties();
        driver = DriverProvider.getActiveDriver();
        setWindowsSize();
        loadFirstPage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverProvider.close();
    }

    @Deprecated
    private void setTestProperty(String key, String value) {
        if (!"".equals(value)) Configuration.setTestValue(key, value);
    }

    @AfterMethod
    @Attachment(value = "ScreenShot", type = "image/png")
    public byte[] takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    private void setWindowsSize() {
        Dimension browserSize = Configuration.getBrowserSize();
        if (browserSize != null) {
            driver.manage().window().setSize(browserSize);
        } else {
            driver.manage().window().maximize();
        }
    }

    @Deprecated
    protected String switchToWindow(int index) {
        DriverProvider.switchActiveWindow(index);
        refreshDriver();
        return DriverProvider.getActiveDriver().equals(driver) ? "primary window" : "secondary window";
    }

    protected abstract void loadFirstPage();
    protected abstract void prepareURLs();
}
