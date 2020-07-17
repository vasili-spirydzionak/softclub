package com.softclub.util;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * @Description:
 * @Author: Vasili Spirydzionak
 * @Date: 7/15/2020
 * @Copyright (c)
 */
public class UiAction {

    public static void pressCtrlEnter(WebDriver driver) {
        Actions builder = new Actions(driver);
        builder.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).perform();
    }
}
