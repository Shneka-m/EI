package util;

import structural.Logger; 
import structural.LoggerAdapter;
import structural.OldLogger;

public class AppLogger {

    private static final java.util.logging.Logger javaLogger = java.util.logging.Logger.getLogger("TaskManagerApp");
    private static final Logger adapterLogger = new LoggerAdapter(new OldLogger());

    private AppLogger() {
        
    }

    public static void info(String message) {
        javaLogger.info(message);
        adapterLogger.log("INFO: " + message);
    }

    public static void warn(String message) {
        javaLogger.warning(message);
        adapterLogger.log("WARN: " + message);
    }

    public static void error(String message, Throwable throwable) {
        javaLogger.severe(message + " | Exception: " + throwable.getMessage());
        adapterLogger.log("ERROR: " + message + " | Exception: " + throwable.getMessage());
    }
}
