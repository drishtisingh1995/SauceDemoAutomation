package saucedemoautomation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	static ExtentReports extentReport;

	public static ExtentReports setUpExtentReport() {
		ExtentSparkReporter report = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/reports/swagslabstestreport.html");
		report.config().setDocumentTitle("Swag Labs Test Report");
		report.config().setReportName("Swag Labs Automation");
		extentReport = new ExtentReports();
		extentReport.attachReporter(report);
		return extentReport;

	}

}
