package com.softclub.page.element.popup;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description:
 * @Author: Vasili Spirydzionak
 * @Date: 7/15/2020
 * @Copyright (c)
 */
@Data
public class NewMessage {
    private By header = By.xpath("//div[text()='New message']");
    private By toInput = By.xpath("//textarea[@name='to']");
    private By subjectInput = By.cssSelector("input[name='subjectbox']");
    private By bodyInput = By.cssSelector("div[aria-label='Message Body']");
    private By sendButton = By.cssSelector("//*[@data-tooltip='Send (Ctrl-enter)']");
}
