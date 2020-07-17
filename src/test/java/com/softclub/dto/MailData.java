package com.softclub.dto;

import com.google.common.base.Objects;
import com.opencsv.bean.CsvBindByName;

import lombok.Data;

/**
 * @Description:
 * @Author: Vasili Spirydzionak
 * @Date: 7/16/2020
 * @Copyright (c)
 */
@Data
public class MailData extends MailDto {
    @CsvBindByName(column="RECIPIENT")
    private String recipient;

    @CsvBindByName(column="SUBJECT")
    private String subject;

    @CsvBindByName(column="MESSAGE")
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailData)) return false;
        MailData mailData = (MailData) o;
        return Objects.equal(recipient, mailData.recipient) &&
                Objects.equal(subject, mailData.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(recipient, subject);
    }


    public static final class MailDataBuilder {
        private String recipient;
        private String subject;
        private String content;

        private MailDataBuilder() {
        }

        public static MailDataBuilder aMailData() {
            return new MailDataBuilder();
        }

        public MailDataBuilder withRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public MailDataBuilder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public MailDataBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public MailData build() {
            MailData mailData = new MailData();
            mailData.setRecipient(recipient);
            mailData.setSubject(subject);
            mailData.setContent(content);
            return mailData;
        }
    }
}
