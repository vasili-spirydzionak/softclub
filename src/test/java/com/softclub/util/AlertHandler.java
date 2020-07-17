package com.softclub.util;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: contains methods for dealing with alerts.
 * @Author: Vasili Spirydzionak
 * @Date: 7/17/2020
 * @Copyright (c)
 */
public class AlertHandler {
    private static Logger LOG = LoggerFactory.getLogger(AlertHandler.class);

    public static void acceptAlert(WebDriver driver) {
        try {
            if (driver.switchTo().alert() != null) {
                driver.switchTo().alert().accept();
            }
        } catch (NoAlertPresentException e) {
            LOG.warn("The alert is not present on the screen {}", e.getCause());
        }
    }

}
