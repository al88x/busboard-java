package training.busboard.logger;

import org.apache.logging.log4j.LogManager;

public class Logger {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static void debug(RuntimeException e) {
        LOGGER.debug(e.getMessage());
    }

    public static void info(String message) {
        LOGGER.info(message);
    }
}
