package com.github.lkapitman.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {

    private static Logger logger = LoggerFactory.getLogger(AppLogger.class);

    public void INFO_LOG(String log_msg) {
        logger.info(log_msg);
    }

    public void ERROR_LOG(String log_msg) {
        logger.error(log_msg);
    }

    public void DEBUG_LOG(String log_msg) {
        logger.debug(log_msg);
    }
}
