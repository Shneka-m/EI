package service;

import util.AppLogger;
import java.util.HashSet;
import java.util.Set;

public class NotificationService {
    private static final Set<String> registeredUsers = new HashSet<>();

    private NotificationService() {} 

    public static void registerUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            AppLogger.warn("Cannot register empty username.");
            return;
        }
        registeredUsers.add(username.trim());
        AppLogger.info("User registered for notifications: " + username);
    }

    public static void notifyUser(String username, String message) {
        if (registeredUsers.contains(username)) {
            System.out.println("🔔 Notification for " + username + ": " + message);
        } else {
            AppLogger.warn("User " + username + " not registered. Notification skipped.");
        }
    }

    public static void notifyAllUsers(String message) {
        if (registeredUsers.isEmpty()) {
            AppLogger.warn("No users registered. Skipping notifications.");
            return;
        }
        System.out.println("🔔 Broadcasting task notification to all users.");
        for (String user : registeredUsers) {
            notifyUser(user, message);
        }
    }

    public static void listUsers() {
        if (registeredUsers.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        System.out.println("Registered Users:");
        for (String user : registeredUsers) {
            System.out.println(" - " + user);
        }
    }
}
