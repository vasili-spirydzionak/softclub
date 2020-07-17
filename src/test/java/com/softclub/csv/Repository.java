package com.softclub.csv;

import com.softclub.dto.MailDto;

import java.util.List;

/**
 * @Description: contains contract for implementation.
 * @Author: Vasili Spirydzionak
 * @Date: 7/16/2020
 * @Copyright (c)
 */
public interface Repository {
    List<? extends MailDto> getEntity(Class<? extends MailDto> clazz);
}
