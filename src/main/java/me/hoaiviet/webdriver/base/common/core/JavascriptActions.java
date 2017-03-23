package me.hoaiviet.webdriver.base.common.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

/**
 * Created by hoaiviet on 1/23/17.
 * Action on page using Javascript
 */
public class JavascriptActions {

    private final JavascriptExecutor js;

    public JavascriptActions(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
    }

    public JavascriptActions click(String cssSelector) {
        js.executeScript("$('" + cssSelector + "').click()");
        return this;
    }

    public JavascriptActions click(WebElement element) {
        js.executeScript("$(arguments[0])[0].click()", element);
        return this;
    }

    public JavascriptActions scrollToElement(WebElement element) {
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

    public JavascriptActions scrollElementIntoViewPort(WebElement element) {
        if (!isElementInViewPort(element)) {
            scrollToElement(element);
        }
        return this;
    }
}
