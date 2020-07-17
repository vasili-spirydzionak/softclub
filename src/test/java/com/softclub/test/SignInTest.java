package com.softclub.test;

import com.softclub.page.InboxPage;
import com.softclub.page.SignInPage;
import com.softclub.util.ColorsCss;

import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @Description: Test class with test cases for google mail box verifications.
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public class SignInTest {
    private static final Logger LOG = LoggerFactory.getLogger(SignInTest.class);
    public static final String MAIN_MENU_COLOR_MSG = "Verify if inbox link in main menu is red";
    public static final String GMAIL_TITLE = "Gmail";
    public static final String INBOX_ENDPOINT = "inbox";

    private SignInPage signInPage;
    private InboxPage inboxPage;
    private SoftAssertions softly;

    @BeforeClass
    public void setUp() {
        signInPage = new SignInPage();
        softly = new SoftAssertions();
    }

    @Test(description = "User signs into gmail and verifies if inbox is opened")
    public void signIn() {
        inboxPage = signInPage.signInToGmail();
        softly.assertThat(inboxPage.getTitle()).isEqualToIgnoringCase(GMAIL_TITLE);
        softly.assertThat(inboxPage.getInboxLinkColor())
                .as(MAIN_MENU_COLOR_MSG)
                .isEqualTo(ColorsCss.RED.name());
        softly.assertThat(inboxPage.getUrl()).contains(INBOX_ENDPOINT);
    }

    @AfterClass()
    public void tearDown() {
        signInPage.tearDownDriver();
    }
}
