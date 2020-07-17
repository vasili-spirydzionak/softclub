package com.softclub.page.element.form;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description: Class for SignIn page object which contains locators for input credentials form.
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
@Data
public class CredentialInputForm {
    private By emailOrPhoneInputLocator = By.xpath("//input[@type ='email']");
    private By passwordInputLocator = By.xpath("//input[@type ='password']");
    private By nextButtonLocator = By.xpath("//*[text()='Next' or text()='Далее']/..");
}
