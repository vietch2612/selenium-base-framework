package webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import webdriver.common.core.HVWebDriver;
import webdriver.common.core.JavascriptAction;
import webdriver.common.core.HVWait;
import webdriver.common.core.driverprovider.DriverProvider;

/**
 * Created by hoaiviet on 5/18/17.
 * webdriver.BasePageObject
 */
public class BasePageObject {

    protected HVWebDriver driver = DriverProvider.getActiveDriver();
    private final Actions actions;
    protected HVWait HVWait;
    protected JavascriptAction jsActions;

    protected BasePageObject() {
        this.actions = new Actions(driver);
        this.HVWait = new HVWait(driver);
        this.jsActions = new JavascriptAction(driver);
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
            throw new SkipException("Can't click this element");
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
            throw new SkipException(String.format("Can't find '%s' in the dropdown list", name));
        }
    }
}
