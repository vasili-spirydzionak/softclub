package com.softclub.page.element.menu;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description: class contains locators from main menu of inbox gmail page.
 * @Author: Vasili Spirydzionak
 * @Date: 7/14/2020
 * @Copyright (c)
 */
@Data
public class MainMenu {
    private By inboxLink = By.linkText("Inbox");
    private By composeButton = By.xpath("//div[text()='Compose' and @role='button']");
    private By newInboxMailMark = By.xpath("//a[text()='Inbox']/../following-sibling::div");

}
