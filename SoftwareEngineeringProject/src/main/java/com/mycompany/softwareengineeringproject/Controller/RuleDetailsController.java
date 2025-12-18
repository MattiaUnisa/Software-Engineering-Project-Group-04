package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.App;
import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.View.DialogManager;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RuleDetailsController {

    @FXML private VBox detailsRoot;
    @FXML private Label lblName;
    @FXML private Label lblTrigger;
    @FXML private Label lblAction;
    @FXML private Label lblStatus;

    private Rule currentRule; // which rule to modify
    
    @FXML
    public void initialize() {
        // if the details sectio is not visible, the list of rules is center
        detailsRoot.managedProperty().bind(detailsRoot.visibleProperty());
    }
    
    // method to close the rule details section
    @FXML
    private void onCloseClick(ActionEvent event) {
        detailsRoot.setVisible(false);
    }
    
    
    // method called by the home to show the details f the rule
    public void setRule(Rule rule) {
        this.currentRule = rule; // save current rule
        
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

    // method to modify the rule, called by the button
    @FXML
    private void onModifyClick(ActionEvent event) {
        if (currentRule == null) return;

        try {
            // load the loader for createOdModifyRule
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/createOrModifyRule.fxml"));
            Parent root = loader.load();

            // get controller and get data
            CreateOrModifyRuleController controller = loader.getController();
            controller.initData(currentRule); // get the rule to  modify

            // 3. Cambia la scena usando il metodo della tua classe App
            // (Assumendo che App.scene sia accessibile o usando un metodo setRoot che accetta Parent)
            App.setRoot(root); 
            
        } catch (IOException e) {
            DialogManager.showError("Error", "Loading Error", "Could not load the modification page.\nError details: " + e.getMessage());
        }
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