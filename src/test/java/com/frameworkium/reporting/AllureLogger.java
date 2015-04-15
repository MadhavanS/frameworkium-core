package com.frameworkium.reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.yandex.qatools.allure.annotations.Step;

public class AllureLogger {
    private static final Logger logger = LogManager.getLogger(AllureLogger.class);

   @Step("Log: {0}")
   public static void LogToAllure(String log)
   {
       logger.info("Logged to allure: " + log);
   }
}
