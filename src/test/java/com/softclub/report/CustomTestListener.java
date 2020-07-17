package com.softclub.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.softclub.config.DriverProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @Description: contains functionality for making test reports.
 * @Author: Vasili Spirydzionak
 * @Date: 7/14/2020
 * @Copyright (c)
 */
public class CustomTestListener implements ITestListener, IConfigurationListener {
    private static final Logger LOG = LoggerFactory.getLogger(CustomTestListener.class);
    public static final String TEST_REPORT_DIR = System.getProperty("user.dir") + "/test-output/";
    private static ExtentReports extentReports = ReportManager.createReports();
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.set(test);
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass("Test passed: " + result.getTestName());
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(String.format("Test %s skipped due to: %s",
                result.getName(), result.getThrowable().getLocalizedMessage()));
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        logFailureWithScreenShot(result);
    }

    /**
     * Logs failure in case it occures in configuration methods.
     * */
    public void onConfigurationFailure(ITestResult result) {
        logFailureWithScreenShot(result);
        DriverProvider.turnOffDriver();
    }

    private void logFailureWithScreenShot(ITestResult result) {
        File scrFile = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
        File destFile = new File(TEST_REPORT_DIR.concat("scrn-").concat(String.valueOf(new Date().getTime())).concat(
                ".png"));
        String scrnPath = destFile.getName();
        try {
            destFile.createNewFile();
            FileUtils.copyFile(scrFile, destFile);
            extentTest.get().fail(String.format("Test %s is failed: \n %s",
                    result.getName(),
                    result.getThrowable().getLocalizedMessage()),
                    MediaEntityBuilder.createScreenCaptureFromPath(scrnPath).build());
        } catch (IOException e) {
            LOG.error("Could not create or copy screenshot to test-output directory {}", e.getCause());
        }
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have run
     * and all their Configuration methods have been called.
     */
    public void onFinish(ITestContext context) {
        extentReports.flush();
        DriverProvider.turnOffDriver();
    }
}
