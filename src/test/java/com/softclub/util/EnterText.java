package com.softclub.util;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: enters text into text input fields
 * @Author: Vasili Spirydzionak
 * @Date: 7/13/2020
 * @Copyright (c)
 */
public class EnterText {
    private static final Logger LOG = LoggerFactory.getLogger(EnterText.class);
    private String text;

    public EnterText(String text) {
        this.text = text;
    }

    /**
     * Enters text char by char with interval of 500 millis
     * @param textInput
     * @throws InterruptedException
     */
    public WebElement into(WebElement textInput) {
        textInput.clear();
        for (int i = 0; i < text.length(); i++) {
            textInput.sendKeys(String.valueOf(text.charAt(i)));
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                LOG.error("Failed to enter text into input field {}", textInput.getTagName());
            }
        }
        return textInput;
    }
}
