package com.softclub.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: configures and provides drivers for corresponding browsers.
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public final class DriverProvider {
    private static final Logger LOG = LoggerFactory.getLogger(DriverProvider.class);
    private static WebDriver driver;
    private static String browserClassName;

    /**
     * Singleton implementation of web driver instantiation.
     *
     * @return Web driver.
     */
    synchronized public static WebDriver getDriver() {
        if (driver == null) {
            LOG.info("Creating Driver for browser {}", Constants.BROWSER_TYPE);
            return createDriver();
        } else {
            LOG.info("Returning existing driver for browser {}", Constants.BROWSER_TYPE);
            return driver;
        }
    }

    /**
     * Creates an instance of a web driver depending on the Constants.BROWSER_TYPE value.
     *
     * @return instance of a web driver.
     */
    private static WebDriver createDriver() {
        DriverManagerType driverManagerType = DriverManagerType.valueOf(Constants.BROWSER_TYPE);
        browserClassName = driverManagerType.browserClass();
        System.out.println(browserClassName);
        WebDriverManager.getInstance(driverManagerType).setup();
        try {
            driver = (WebDriver) Class.forName(browserClassName).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LOG.error("Failed to create new web-driver instance for browser {}", Constants.BROWSER_TYPE);
            e.printStackTrace();
        }
        return driver;
    }

    public static void turnOffDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
