package com.softclub.page.element.header;

import com.softclub.config.Constants;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description: class contains locators for gmail header bar of inbox page.
 * @Author: Vasili Spirydzionak
 * @Date: 7/14/2020
 * @Copyright (c)
 */
@Data
public class GmailHeaderBar {
    private By gmailLink = By.cssSelector("a[href='#inbox']");
    private By gmailLogo = By.cssSelector("img[src*=logo_gmail]");
    private By accountLabel = By.cssSelector(String.format("a[aria-label*='%s']", Constants.LOGIN));
}
