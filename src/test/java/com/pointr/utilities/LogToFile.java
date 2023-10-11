package com.pointr.utilities;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogToFile {
    static final Logger logger = LoggerFactory.getLogger(LogToFile.class);

    public static final String RESULTS_FILE_PATH = "src" + File.separator + "test" + File.separator + "java" + File.separator + "com" + File.separator +
            "pointr" + File.separator + "results" + File.separator;

    public static void saveLogToFile(String log, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESULTS_FILE_PATH + filename, true))) {
            logger.info("Log is saving: " + filename);
            writer.println(log);
        } catch (IOException e) {
            logger.error("RECORDS COULD NOT BE SAVED TO: " + filename);
            e.printStackTrace();
        }
    }

    public static void saveLogAndScreenshotToFile(String log, String screenshotPath, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESULTS_FILE_PATH + filename, true))) {
            logger.info("Log is saving: " + filename);
            writer.println(log);
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                writer.println("Screenshot: " + screenshotPath);
                writer.println("=======================================================================================================================================================");
            }
        } catch (IOException e) {
            logger.error("RECORDS COULD NOT BE SAVED TO: " + filename);
            e.printStackTrace();
        }
    }

    public static void clearLogFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESULTS_FILE_PATH + filename))) {
            logger.info("Logs are cleared from: " + filename);
        } catch (IOException e) {
            logger.error("RECORDS COULD NOT BE CLEARED FROM: " + filename);
            e.printStackTrace();
        }
    }

    public static void clearAllLogFiles() {
        String resultsFilePath = System.getProperty("user.dir") + File.separator + RESULTS_FILE_PATH;
        File directory = new File(resultsFilePath);
        File[] files = directory.listFiles();

        for (File file : files) {
            String fileName = file.getName();
            if (fileName.equals("screenshots")) {
                try {
                    File dir = new File(resultsFilePath + File.separator + fileName);
                    File[] dir_contents = dir.listFiles();

                    for (int i = 0; i < dir_contents.length; i++) {
                        if (dir_contents[i].isDirectory()) {
                            try {
                                FileUtils.deleteDirectory(dir_contents[i]);
                            } catch (IOException e) {
                                System.out.println("Folder is empty");
                                System.out.println("Failed");
                                e.printStackTrace();
                            }
                        } else {
                            dir_contents[i].delete();
                        }
                    }
                    dir.delete();
                } catch (Exception e) {

                }
            } else {
                try (PrintWriter writer = new PrintWriter(new FileWriter(RESULTS_FILE_PATH + fileName))) {
                    logger.info("Logs are cleared from: " + fileName);
                } catch (IOException e) {
                    logger.error("RECORDS COULD NOT BE CLEARED FROM: " + fileName);
                    e.printStackTrace();
                }
            }
        }
    }
}

