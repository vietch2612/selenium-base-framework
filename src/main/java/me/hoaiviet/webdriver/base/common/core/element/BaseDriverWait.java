package me.hoaiviet.webdriver.base.common.core.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by hoaiviet on 12/8/16.
 * SS Waiter
 */
public class BaseDriverWait {

    private static final int DEFAULT_TIMEOUT = 15;
    private final WebDriverWait wait;

    public BaseDriverWait(WebDriver driver) {
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public void forUrlContain(String url) {
        wait.until(ExpectedConditions.urlContains(url));
    }

    public void forElementNotVisible(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement forElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement forElementVisible(WebElement element) {
        element.getTagName();
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
