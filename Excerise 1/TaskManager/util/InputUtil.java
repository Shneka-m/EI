package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import util.AppLogger;
import util.AppException;

public final class InputUtil {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private InputUtil() {
       
    }

    public static int readInt(String prompt) throws AppException {
        while (true) {
            System.out.print(prompt);
            try {
                String line = reader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    AppLogger.warn("Input cannot be empty. Try again.");
                    continue;
                }
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                AppLogger.warn("Invalid integer. Try again.");
            } catch (IOException e) {
                throw new AppException("Error reading input", e);
            }
        }
    }

    public static String readNonEmpty(String prompt) throws AppException {
        while (true) {
            System.out.print(prompt);
            try {
                String line = reader.readLine();
                if (line == null || line.trim().isEmpty()) {
                    AppLogger.warn("Input cannot be empty. Try again.");
                    continue;
                }
                return line.trim();
            } catch (IOException e) {
                throw new AppException("Error reading input", e);
            }
        }
    }

    public static String readLineOptional() {
        try {
            String line = reader.readLine();
            return line == null ? "" : line.trim();
        } catch (IOException e) {
            AppLogger.error("Error reading optional input", e);
            return "";
        }
    }
}
