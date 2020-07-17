package com.softclub.page;

import com.softclub.config.DriverProvider;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softclub.util.WaitTimeouts.MIDDLE;
import static com.softclub.util.WaitTimeouts.MIN;

/**
 * @Description: page contains common functionality for all Page Objects.
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public abstract class AbstractPage {
    protected WebDriver driver;

    protected AbstractPage() {
        this.driver = DriverProvider.getDriver();
        driver.manage().window().maximize();
    }

    /**
     * clicks on element found by locator.
     *
     * @param locator - By locator
     */
    protected void clickOn(By locator) {
        ((WebElement) getFluentWait().until(getElementFunction(locator))).click();
    }

    protected void clickOn(WebElement element) {
        new WebDriverWait(driver, MIDDLE.getTimeouts())
                .pollingEvery(Duration.ofSeconds(MIN.getTimeouts()))
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private Function<WebDriver, WebElement> getElementFunction(By locator) {
        return (driver) -> {
            return driver.findElement(locator);
        };
    }

    protected WebElement getVisibleElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(MIDDLE.getTimeouts()).getSeconds())
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> getElements(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(MIDDLE.getTimeouts()).getSeconds())
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected WebElement getLinkElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(MIDDLE.getTimeouts()).getSeconds())
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitTillPageElementsLoaded(By locator) {
        driver.manage().timeouts().pageLoadTimeout(MIDDLE.getTimeouts(), TimeUnit.SECONDS);
        new WebDriverWait(driver, MIDDLE.getTimeouts()).until(scriptLoadConditionFunction());
        new WebDriverWait(driver, MIDDLE.getTimeouts()).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private Function<WebDriver, Boolean> scriptLoadConditionFunction() {
        return (driver) -> {
            return ((JavascriptExecutor) driver).executeScript(
                    "return document.readyState").equals("complete");
        };
    }

    private FluentWait getFluentWait() {
        return new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(MIDDLE.getTimeouts()))
                .pollingEvery(Duration.ofSeconds(MIN.getTimeouts()))
                .ignoring(WebDriverException.class);
    }

    protected void waitTill(int timeout, ExpectedCondition condition) {
        new WebDriverWait(driver, timeout).pollingEvery(Duration.ofMillis(MIN.getTimeouts()))
                .until(condition);
    }

    public void tearDownDriver() {
        DriverProvider.turnOffDriver();
    }
}