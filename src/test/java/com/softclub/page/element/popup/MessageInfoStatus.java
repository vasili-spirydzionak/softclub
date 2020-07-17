package com.softclub.page.element.popup;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description: class contains locators for message status info pop up.
 * @Author: Vasili Spirydzionak
 * @Date: 7/16/2020
 * @Copyright (c)
 */
@Data
public class MessageInfoStatus {
    private By sentInfoMsg = By.xpath("//*[text()='Message sent.']");
    private By sendingInfoMsg = By.xpath("//*[text()='Sending...']");
}
