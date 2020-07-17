package com.softclub.test;

import com.softclub.csv.CsvRepositoryImpl;
import com.softclub.dto.MailData;
import com.softclub.page.InboxPage;
import com.softclub.page.MailDetailViewPage;
import com.softclub.page.SignInPage;

import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description: Test class with test cases for google mail box verifications.
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public class EmailTest {
    private static final Logger LOG = LoggerFactory.getLogger(EmailTest.class);
    public static final String NEW_EMAIL_MARK_MSG = "Verify new email mark appears in the 'Inbox' main menu item";
    public static final String MAIL_SENT_MSG = "Verify that the mail is sent by 'Me'.";

    private SignInPage signInPage;
    private InboxPage inboxPage;
    private SoftAssertions softly;
    private CsvRepositoryImpl repo;
    private MailDetailViewPage mailDetailViewPage;

    private String csvFilePath = buildPathToCsv();

    private String buildPathToCsv() {
        return String.format("fixture/%s.csv", (this.getClass().getName().replaceAll(".*(?<=\\.)",
                "")));
    }

    @BeforeClass
    public void setUp() {
        signInPage = new SignInPage();
        softly = new SoftAssertions();
        repo = new CsvRepositoryImpl(csvFilePath);
        inboxPage = signInPage.signInToGmail();
    }

    @Test(description = "User creates mail and sends it to himself")
    public void verifyEmailSending() {
        MailData expectedMailData = (MailData) repo.getEntity(MailData.class).get(0);
        inboxPage.writeEmail(expectedMailData);
        assertThat(inboxPage.isNewMailAppear()).as(NEW_EMAIL_MARK_MSG).isTrue();
        assertThat(inboxPage.getIncomingEmails().stream()
                .map(email -> email.getText())
                .anyMatch(txt -> txt.contains("me") && txt.contains(expectedMailData.getSubject()))
        ).as(MAIL_SENT_MSG).isTrue();
        mailDetailViewPage = inboxPage.openNewMail();

        assertThat(expectedMailData).isEqualTo(mailDetailViewPage.getEmailData());
    }

    @AfterClass()
    public void tearDown() {
        inboxPage.signOut().tearDownDriver();
    }
}
