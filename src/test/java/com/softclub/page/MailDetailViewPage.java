package com.softclub.page;

import com.softclub.dto.MailData;

import lombok.Data;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Description: contains functionality of separate email page.
 * @Author: Vasili Spirydzionak
 * @Date: 7/16/2020
 * @Copyright (c)
 */
@Data
public class MailDetailViewPage extends AbstractPage {
    private WebDriver driver;

    private By subjectHeader = By.xpath("//*[@aria-label='Important according to Google magic.']/." +
            "./preceding-sibling::h2");
    private By emailHeader = By.xpath("//h3//span[@aria-hidden='true']/..");


    public MailDetailViewPage() {
        super();
        this.driver = super.driver;
    }

    public MailData getEmailData() {
        return MailData.MailDataBuilder.aMailData()
                .withRecipient(getVisibleElement(emailHeader).getText().replaceAll("<|>", ""))
                .withSubject(getVisibleElement(subjectHeader).getText())
                .build();
    }
}
