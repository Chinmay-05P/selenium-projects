package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {
	
	public static ExtentReports getInstance() {
		ExtentReports extent = new ExtentReports();
		/*
		String path = System.getProperty("user.dir") + "//ExtentReports//report.html";
		extent = new ExtentReports(path, false); 
		*/
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "//ExtentReports//index.html");
		spark.config().setDocumentTitle("Automation report");
		extent.attachReporter(spark);
		return extent;
	}

}
