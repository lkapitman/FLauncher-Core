package com.github.lkapitman.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type App logger.
 */
public class AppLogger {

    private static Logger logger = LoggerFactory.getLogger(AppLogger.class);

    /**
     * Info log.
     *
     * @param log_msg the log msg
     */
    public void INFO_LOG(String log_msg) {
        logger.info(log_msg);
    }

    /**
     * Error log.
     *
     * @param log_msg the log msg
     */
    public void ERROR_LOG(String log_msg) {
        logger.error(log_msg);
    }

    /**
     * Debug log.
     *
     * @param log_msg the log msg
     */
    public void DEBUG_LOG(String log_msg) {
        logger.debug(log_msg);
    }
}
