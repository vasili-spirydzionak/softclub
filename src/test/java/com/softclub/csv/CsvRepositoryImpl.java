package com.softclub.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.softclub.dto.MailData;
import com.softclub.dto.MailDto;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: class contains functionality for reading data from csv file
 * @Author: Vasili Spirydzionak
 * @Date: 7/16/2020
 * @Copyright (c)
 */
public class CsvRepositoryImpl implements Repository {
    private static final Logger LOG = LoggerFactory.getLogger(CsvRepositoryImpl.class);
    private String csvPath;

    /**
     * Constructor creates an instance or the class and creates a path to the csv file with input test-data.
     * Path gets created based on the name of a test class which tests input data geared for.
     * @param csvPath - path to the csv file with test data.
     */
    public CsvRepositoryImpl(String csvPath) {
        this.csvPath = this.getClass()
                .getClassLoader()
                .getResource(csvPath).getPath().replaceFirst("/", "");
    }

    /**
     * Maps data from csv row into an instance.
     * @param clazz - Class of an instance which retreived from csv data is mapped into.
     * @return list of instances of a MailData
     */
    public List<? extends MailDto> getEntity(Class<? extends MailDto> clazz) {
        List<MailData> mailData = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath))) {
            mailData = new CsvToBeanBuilder(reader).withType(clazz).build().parse();
        } catch (IOException e) {
            LOG.error("Failed to create csv reader {}", e.getLocalizedMessage());
        }
        return mailData;
    }
}
