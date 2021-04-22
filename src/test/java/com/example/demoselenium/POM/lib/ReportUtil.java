package com.example.demoselenium.POM.lib;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtil {
	public static ExtentHtmlReporter getExtentHtmlReporter() {
		String currentTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("IO_Data_" + currentTime + "_" + "REPORT.html");
		htmlReporter.config().setDocumentTitle("REPORT");
		htmlReporter.config().setReportName("REPORT RESULT");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.setAppendExisting(true);
		return htmlReporter;
	}
	
//	public static void log(ExtentTest extentTest, boolean result) {
//		if (result) {
//			extentTest.pass("pass");
//		} else {
//			extentTest.fail("fail");
//		}
//	}
}

