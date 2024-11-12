package com.petproject.ai_data_crawler.utils;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;

@UtilityClass
public class ThrowableUtils {

    /**
     * Method is designed for use in lambda expressions, when something needs to be returned but useless;
     * Return null instead
     * @param log logger instance
     * @param message error message to print, including placeholders
     * @param throwable exception to log
     * @param args arguments for placeholders
     * @return null
     * @param <T> doesn't matter. Method is designed for use as a mock in lambda expressions
     */
    public <T> T handle(Logger log, String message, Throwable throwable, String ...args) {
        log.error(message, args, throwable);
        return null;
    }
}
