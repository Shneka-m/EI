package structural;

public class LoggerAdapter implements Logger {
    private final OldLogger oldLogger;

    public LoggerAdapter(OldLogger oldLogger) {
        this.oldLogger = oldLogger;
    }

    @Override
    public void log(String message) {
        oldLogger.writeLog(message);
    }
}
