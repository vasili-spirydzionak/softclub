package com.softclub.page;

import com.softclub.page.element.form.PresentationForm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @Description:
 * @Author: Vasili Spirydzionak
 * @Date: 7/15/2020
 * @Copyright (c)
 */
public class AccountsPage extends AbstractPage {
    private WebDriver driver;
    private PresentationForm presentationForm = new PresentationForm();

    public AccountsPage() {
        super();
        this.driver = super.driver;
        waitTillPageElementsLoaded(presentationForm.getGoogleLogo());
    }

    public WebElement getSignedOutAccountElement() {
        return getVisibleElement(presentationForm.getAccountStatusIdentifier());
    }
}
