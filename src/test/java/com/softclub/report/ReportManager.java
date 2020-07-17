package com.softclub.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: creates instance of extents report.
 * @Author: Vasili Spirydzionak
 * @Date: 7/17/2020
 * @Copyright (c)
 */
public class ReportManager {
    private static final Logger LOG = LoggerFactory.getLogger(ReportManager.class);
    public static final String TEST_REPORT_DIR = System.getProperty("user.dir") + "/test-output/";
    public static final String REPORT_FILE_PATH = TEST_REPORT_DIR + "ExtentReport.html";

    /**
     * Creates reports and reporters, makes basic configurations.
     * @return instance of extents reports.
     */
    public static ExtentReports createReports() {
        ExtentReports extentReports = new ExtentReports();
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(REPORT_FILE_PATH);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle(REPORT_FILE_PATH);
        reporter.config().setEncoding("utf-8");
        reporter.config().setReportName(REPORT_FILE_PATH);
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
