package webdriver.common.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

/**
 * Created by hoaiviet on 5/18/17.
 * JavascriptAction
 */
public class JavascriptAction {

    private final JavascriptExecutor js;

    public JavascriptAction(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public JavascriptAction click(String cssSelector) {
        js.executeScript("$('" + cssSelector + "').click()");
        return this;
    }

    public JavascriptAction click(WebElement element) {
        js.executeScript("$(arguments[0])[0].click()", element);
        return this;
    }

    public JavascriptAction scrollToElement(WebElement element) {
        try {
            js.executeScript(
                    "var x = $(arguments[0]);" + "window.scroll(0,parseInt(x.offset().top - 60));", element);
        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new SkipException("Cannot scroll to this Element");
        }
        return this;
    }

    private boolean isElementInViewPort(WebElement element) {
        return (Boolean) js.executeScript(
                "return ($(window).scrollTop() + 60 < $(arguments[0]).offset().top) && ($(window).scrollTop() "
                        + "+ $(window).height() > $(arguments[0]).offset().top + $(arguments[0]).height() + 60)",
                element);
    }

    public JavascriptAction scrollElementIntoViewPort(WebElement element) {
        if (!isElementInViewPort(element)) {
            scrollToElement(element);
        }
        return this;
    }
}
