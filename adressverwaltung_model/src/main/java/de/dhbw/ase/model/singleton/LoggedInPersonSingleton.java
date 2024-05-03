package de.dhbw.ase.model.singleton;

public class LoggedInPersonSingleton {

    private static LoggedInPersonSingleton instance;

    // Instance variable to hold the currently logged-in user
    private static long loggedInUserID;

    // Private constructor to prevent instantiation from outside
    private LoggedInPersonSingleton() {
    }

    public static LoggedInPersonSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggedInPersonSingleton.class) {
                if (instance == null) {
                    instance = new LoggedInPersonSingleton();
                }
            }
        }
        return instance;
    }

    public static void setLoggedInUserID(long userID) {
        instance.loggedInUserID = userID;
    }

    public static long getLoggedInUserID() {
        return loggedInUserID;
    }
}
