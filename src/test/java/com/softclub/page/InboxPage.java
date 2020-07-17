package com.softclub.page;

import com.softclub.dto.MailData;
import com.softclub.page.element.header.GmailHeaderBar;
import com.softclub.page.element.menu.MainMenu;
import com.softclub.page.element.popup.AccountInformation;
import com.softclub.page.element.popup.MessageInfoStatus;
import com.softclub.page.element.popup.NewMessage;
import com.softclub.page.element.table.InboxMailPanel;
import com.softclub.util.AlertHandler;
import com.softclub.util.EnterText;
import com.softclub.util.UiAction;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softclub.util.WaitTimeouts.MAX;
import static com.softclub.util.WaitTimeouts.MIDDLE;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * @Description: Page object for inbox gmail page.
 * @Author: Vasili Spirydzionak
 * @Date: 7/14/2020
 * @Copyright (c)
 */
public class InboxPage extends AbstractPage {
    private WebDriver driver;

    // Nested html page ui elements
    private MainMenu mainMenu = new MainMenu();
    private GmailHeaderBar gmailHeaderBar = new GmailHeaderBar();
    private AccountInformation accountInfoPopUp = new AccountInformation();
    private NewMessage newMessage = new NewMessage();
    private InboxMailPanel inboxMailPanel = new InboxMailPanel();
    private MessageInfoStatus msgInfoStatus = new MessageInfoStatus();

    public InboxPage() {
        super();
        this.driver = super.driver;
        super.waitTillPageElementsLoaded(gmailHeaderBar.getGmailLogo());
    }

    public String getTitle() {
        return getLinkElement(gmailHeaderBar.getGmailLink()).getAttribute("title");
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getInboxLinkColor() {
        return getVisibleElement(mainMenu.getInboxLink()).getCssValue("color");
    }

    public AccountsPage signOut() {
        clickOn(gmailHeaderBar.getAccountLabel());
        clickOn(accountInfoPopUp.getSignOutLink());
        AlertHandler.acceptAlert(driver);
        return new AccountsPage();
    }

    public InboxPage writeEmail(MailData mailData) {
        clickOn(mainMenu.getComposeButton());
        fillInMailInputFields(mailData);
        UiAction.pressCtrlEnter(driver);
        waitTill(MIDDLE.getTimeouts(), visibilityOfElementLocated(msgInfoStatus.getSentInfoMsg()));
        return this;
    }

    private void fillInMailInputFields(MailData mailData) {
        new EnterText(mailData.getRecipient()).into(getVisibleElement(newMessage.getToInput()));
        new EnterText(mailData.getSubject()).into(getVisibleElement(newMessage.getSubjectInput()));
        new EnterText(mailData.getContent()).into(getVisibleElement(newMessage.getBodyInput()));
    }

    public List<WebElement> getIncomingEmails() {
        List<WebElement> emails = getElements(inboxMailPanel.getIncomingEmails());
        System.out.println(emails.size());
        return emails;
    }

    public boolean isNewMailAppear() {
        return new WebDriverWait(driver, MAX.getTimeouts())
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver driver) -> {
                    return driver.findElement(mainMenu.getNewInboxMailMark()).isDisplayed();
                });
    }

    public MailDetailViewPage openNewMail() {
        clickOn(getIncomingEmails().get(0));
        return new MailDetailViewPage();
    }
}
