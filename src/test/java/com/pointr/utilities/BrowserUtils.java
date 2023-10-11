package com.pointr.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.pointr.utilities.WaitUtils.waitUntilClickable;

public class BrowserUtils {

    public static void clickElement(WebElement element){
        waitUntilClickable(element).click();
    }

    public static String takeScreenshot(String name) {
        name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;
        String path = LogToFile.RESULTS_FILE_PATH + "screenshots" + File.separator + name + ".png";
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(path);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.getProperty("user.dir") + File.separator + path;
    }
}
