package com.softclub.page.element.table;

import lombok.Data;

import org.openqa.selenium.By;

/**
 * @Description: class contains locators for inbox mail panel.
 * @Author: Vasili Spirydzionak
 * @Date: 7/15/2020
 * @Copyright (c)
 */
@Data
public class InboxMailPanel {
    private By incomingEmails = By.xpath("//table[@role='grid']/tbody/tr[@role='row']");
    private By emailGridCell = By.xpath("/td[@role='gridcell']");
    private By newMailCell = By.xpath("//span[@name='me']/ancestor::td[@role='gridcell']");
}
