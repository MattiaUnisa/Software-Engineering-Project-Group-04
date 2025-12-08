package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.App;
import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import com.mycompany.softwareengineeringproject.Model.Trigger;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CreateRuleController {
    
    // takes the name from the tag in fxml
    @FXML 
    private TextField nameField;
    
    @FXML
    private TriggerController triggerSectionController;
    

     // Go back to the home screen saving nothing
    @FXML
    private void onBackClick() throws IOException {
        App.setRoot("home");
    }

    // go back to the home screen saving the rule
    @FXML
    private void onSaveClick() throws IOException {
        String name = nameField.getText();
        
        Trigger trigger = triggerSectionController.buildTrigger();

        // Validation of the name
        if (name == null || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Name missing");
            alert.setContentText("Please insert a name for the rule.");
            alert.showAndWait();
            return; 
        }
        
        if (trigger == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Trigger missing");
            alert.setContentText("Please select a trigger before saving the rule.");
            alert.showAndWait();
            return;
        }
        
        // empty rule 
        Rule newRule = new Rule(name, trigger, null);

        // add the rule to the singleton 
        RuleEngine.getInstance().addRule(newRule);
        
        // debug in console
        System.out.println("Rule Saved: " + name); 
        
        // TO DO
        
        App.setRoot("home");
    }
    
}
