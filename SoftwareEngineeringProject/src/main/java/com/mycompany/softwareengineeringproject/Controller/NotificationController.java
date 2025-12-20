package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.NotificationAction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NotificationController implements ActionControllerInterface {

    @FXML
    private TextField messageField;

    @Override
    public Action buildAction() {
        String msg = (messageField != null) ? messageField.getText() : "";
        if (msg == null) msg = "";
        return ActionFactory.createShowNotification(msg);
    }
    
    // method to get the instance of the NotificationAction to set the spinner values in the UI
    // called by setAction method in ActionController
    @Override
    public void setActionData(Action action) {
        if (action instanceof NotificationAction) {
            NotificationAction notifyAction = (NotificationAction) action;
            messageField.setText(notifyAction.getMessage());
        }
    }
}
