package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
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
}
