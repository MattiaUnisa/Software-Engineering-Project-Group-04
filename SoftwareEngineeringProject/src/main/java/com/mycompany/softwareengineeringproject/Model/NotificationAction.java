package com.mycompany.softwareengineeringproject.Model;

import com.mycompany.softwareengineeringproject.View.DialogManager;

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
        String title = "Notification";
        if (message == null || message.isEmpty()) {
            context.appendToLog("No Message is given by the user");
            return;
        }
        //Get the listener, verify if it is null and if not call the method onShowNotification in order to Show a notification
        if (context.getUiEventListener() != null) {
            context.getUiEventListener().onShowNotification("Notification", message);
        }   
        stop();
    }

    /**
     * This action has nothing to stop, so this method stays empty.
     */
    @Override
    public void stop() {
        // No stop behavior needed for a simple notification
    }
    
    @Override
    public String formatString(){
        return "Notification:" + this.message;
    }
    
    public static Action parseString(String action){
        if (!action.startsWith("Notification:")) {
            throw new IllegalArgumentException("Invalid Notification format.");
        }
        String message = action.substring("Notification:".length());
        //Use the factory to create the object
        return ActionFactory.createShowNotification(message);
    }

    @Override
    public String toString() {
        return "NotificationAction{message='" + message + "'}";
    }
}
