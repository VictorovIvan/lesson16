package jdbc;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import java.util.UUID;

/**
 * Class LoggerSet
 */
public class LoggerSet {
    /**
     * Set info for write to database
     *
     * @param logger          Input logger
     * @param exceptionString Message for exception in database
     * @param message         Message for write to message in database
     */
    public void setInfo(Logger logger, String exceptionString, String message) {
        MDC.put("id", UUID.randomUUID().toString());
        MDC.put("exception", exceptionString);
        logger.info(message);
    }

    /**
     * Debug debug for write to database
     *
     * @param logger          Input logger
     * @param exceptionString Message for exception in database
     * @param message         Message for write to message in database
     */
    public void setDebug(Logger logger, String exceptionString, String message) {
        MDC.put("id", UUID.randomUUID().toString());
        MDC.put("exception", exceptionString);
        logger.debug(message);
    }

    /**
     * Set error for write to database
     *
     * @param logger          Input logger
     * @param exceptionString Message for exception in database
     * @param message         Message for write to message in database
     */
    public void setError(Logger logger, String exceptionString, String message) {
        MDC.put("id", UUID.randomUUID().toString());
        MDC.put("exception", exceptionString);
        logger.error(message);
    }
}
