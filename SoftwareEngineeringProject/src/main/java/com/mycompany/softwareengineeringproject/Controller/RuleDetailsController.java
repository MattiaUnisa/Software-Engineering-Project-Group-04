package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Rule;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RuleDetailsController {

    @FXML private VBox detailsRoot;
    @FXML private Label lblName;
    @FXML private Label lblTrigger;
    @FXML private Label lblAction;
    @FXML private Label lblStatus;

    @FXML
    public void initialize() {
        // if the details sectio is not visible, the list of rules is center
        detailsRoot.managedProperty().bind(detailsRoot.visibleProperty());
    }
    
    // method called by the home to show the details f the rule
    public void setRule(Rule rule) {
        if (rule == null) {
            detailsRoot.setVisible(false);
            return;
        }

        // show the panel
        detailsRoot.setVisible(true);

        // set texts
        lblName.setText(rule.getName());
        lblTrigger.setText(rule.getTrigger().toString());
        lblAction.setText(rule.getAction().toString());

        
        updateStatusStyle(rule.isActive());
    }

    // handle styles for active/inactive states 
    // look at css
    private void updateStatusStyle(boolean isActive) {
        if (isActive) {
            lblStatus.setText("Active (ON)");
            lblStatus.getStyleClass().remove("status-inactive");
            if (!lblStatus.getStyleClass().contains("status-active")) {
                lblStatus.getStyleClass().add("status-active");
            }
        } else {
            lblStatus.setText("Inactive (OFF)");
            lblStatus.getStyleClass().remove("status-active");
            if (!lblStatus.getStyleClass().contains("status-inactive")) {
                lblStatus.getStyleClass().add("status-inactive");
            }
        }
    }
    
    // return the visibility of the panel (used in homeController)
    public BooleanProperty visibleProperty() {
        return detailsRoot.visibleProperty();
    }
}