package com.softclub.page;

import com.softclub.config.Constants;
import com.softclub.page.element.form.CredentialInputForm;
import com.softclub.util.EnterText;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Description: Page object for initial google page.
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public class SignInPage extends AbstractPage {
    private static final String URL = "https://gmail.com";

    private WebDriver driver;
    private CredentialInputForm credInputForm = new CredentialInputForm();

    public SignInPage() {
        super();
        this.driver = super.driver;
        driver.get(URL);
        super.waitTillPageElementsLoaded(credInputForm.getNextButtonLocator());
    }

    public InboxPage signInToGmail() {
        enterLogin();
        clickNextButton();
        enterPassword();
        clickNextButton();
        return new InboxPage();
    }

    private void enterPassword() {
        By passwordInput = credInputForm.getPasswordInputLocator();
        new EnterText(Constants.PASSWORD).into(getVisibleElement(passwordInput));
    }

    private void enterLogin() {
        By emailOrPhoneInput = credInputForm.getEmailOrPhoneInputLocator();
        new EnterText(Constants.LOGIN).into(getVisibleElement((emailOrPhoneInput)));
    }

    private void clickNextButton() {
        clickOn(credInputForm.getNextButtonLocator());
    }

}
