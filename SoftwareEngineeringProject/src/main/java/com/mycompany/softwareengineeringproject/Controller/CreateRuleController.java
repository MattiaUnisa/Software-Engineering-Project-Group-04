package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.App;
import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CreateRuleController {
    
    // takes the name from the tag in fxml
    @FXML 
    private TextField nameField;

     // Go back to the home screen saving nothing
    @FXML
    private void onBackClick() throws IOException {
        App.setRoot("home");
    }

    // go back to the home schreen saving the rule
    @FXML
    private void onSaveClick() throws IOException {
        String name = nameField.getText();
        
        // Validation of the name
        if (name == null || name.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore");
            alert.setHeaderText("Name missing");
            alert.setContentText("Please insert a name for the rule.");
            alert.showAndWait();
            return; 
        }
        
        // empty rule 
        Rule newRule = new Rule(name, null, null);

        // add the rule to the singleton 
        RuleEngine.getInstance().addRule(newRule);
        
        // debug in console
        System.out.println("Rule Saved: " + name); 
        
        // TO DO
        
        App.setRoot("home");
    }
}