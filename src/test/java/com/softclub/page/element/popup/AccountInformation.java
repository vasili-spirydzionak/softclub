package com.softclub.page.element.popup;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description: class contains locators from Account information form.
 * @Author: Vasili Spirydzionak
 * @Date: 7/15/2020
 * @Copyright (c)
 */
@Data
public class AccountInformation {
    private By signOutLink = By.linkText("Sign out");
}
