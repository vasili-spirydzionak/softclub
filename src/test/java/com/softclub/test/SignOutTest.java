package com.softclub.test;

import com.softclub.config.Constants;
import com.softclub.page.AccountsPage;
import com.softclub.page.InboxPage;
import com.softclub.page.SignInPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description: Test class with test cases for google mail box verifications
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public class SignOutTest {
    private static final Logger LOG = LoggerFactory.getLogger(SignOutTest.class);

    private SignInPage signInPage;
    private InboxPage inboxPage;
    private AccountsPage accountsPage;

    @BeforeClass
    public void setUp() {
        signInPage = new SignInPage();
        inboxPage = signInPage.signInToGmail();
    }

    @Test(description = "User signs in to gmail and signs out")
    public void verifyEmailSingOut() {
        accountsPage = inboxPage.signOut();
        assertThat(accountsPage.getSignedOutAccountElement().getText()).contains(Constants.LOGIN);
    }

    @AfterClass()
    public void tearDown() {
        accountsPage.tearDownDriver();
    }
}
