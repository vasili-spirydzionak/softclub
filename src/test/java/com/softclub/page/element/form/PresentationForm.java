package com.softclub.page.element.form;

import com.softclub.config.Constants;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description:
 * @Author: Vasili Spirydzionak
 * @Date: 7/15/2020
 * @Copyright (c)
 */
@Data
public class PresentationForm {
    private By accountStatusIdentifier = By.cssSelector(String.format("div[data-identifier='%s']", Constants.LOGIN));
    private By googleLogo = By.cssSelector("#logo");
}
