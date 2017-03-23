package me.hoaiviet.webdriver.base;

import me.hoaiviet.webdriver.base.common.core.JavascriptActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import me.hoaiviet.webdriver.base.common.core.BaseWebDriver;
import me.hoaiviet.webdriver.base.common.core.driverprovider.DriverProvider;
import me.hoaiviet.webdriver.base.common.core.element.BaseDriverWait;

/**
 * Created by hoaiviet on 12/7/16.
 * Silicon Straits Base Page Object
 */
public class BasePageObject {

    protected BaseWebDriver driver = DriverProvider.getActiveDriver();
    private final Actions actions;
    protected BaseDriverWait sssWait;
    protected JavascriptActions jsActions;

    protected BasePageObject() {
        this.actions = new Actions(driver);
        this.sssWait = new BaseDriverWait(driver);
        this.jsActions = new JavascriptActions(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * @param url String URL
     */
    public void getUrl(String url) {
        driver.get(url);
    }

    /**
     * @param element WebElement, when the element is not visible to click
     */
    protected void clickElementByActions(WebElement element) {
        try {
            actions.moveToElement(element).click().perform();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SkipException("Can't click this Element");
        }
    }

    /**
     * @param element WebElement, when the element is not visible to click
     */
    protected void clickElementByJsExecutor(WebElement element) {
        JavascriptExecutor jse = driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
        element.click();
    }

    /**
     * @param element WebElement(TextField) you want to clear text
     * @return element with empty value
     */
    protected WebElement clearText(WebElement element) {
        element.clear();
        return element;
    }

    /**
     * @param element WebElement dropdown list
     * @param name visible name
     */
    protected void selectByVisibleText(WebElement element, String name) {
        Select select = new Select(element);
        try {
            select.selectByVisibleText(name);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SkipException(String.format("Can't find %s in dropdown list", name));
        }
    }
}
