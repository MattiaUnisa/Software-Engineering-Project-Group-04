package com.mycompany.softwareengineeringproject.Model;

/**
 * A simple action that shows a notification message when a rule is triggered.
 */
public class NotificationAction implements Action {

    // The text that will be shown to the user
    private final String message;

    /**
     * Constructor: Stores the message written by the user.
     */
    public NotificationAction(String message) {
        this.message = message;
    }

    /**
     * Returns the message (useful for the UI or logs).
     */
    public String getMessage() {
        return message;
    }

    /**
     * When the rule is triggered, the RuleEngine calls execute().
     * Here is where we show the notification.
     * For now, we just print it. Later you can show it with JavaFX.
     */
    @Override
    public void execute(ActionContext context) {
        System.out.println("NOTIFICATION: " + message);
        // TODO: Replace this with a graphical popup (Alert) later
    }

    /**
     * This action has nothing to stop, so this method stays empty.
     */
    @Override
    public void stop() {
        // No stop behavior needed for a simple notification
    }

    @Override
    public String toString() {
        return "NotificationAction{message='" + message + "'}";
    }
}
