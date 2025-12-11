package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.App;
import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.Repetition;
import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import com.mycompany.softwareengineeringproject.Model.Trigger;
import com.mycompany.softwareengineeringproject.View.DialogManager;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateRuleController {
    
    // takes the name from the tag in fxml
    @FXML 
    private TextField nameField;
    
    @FXML
    private TriggerController triggerSectionController;
    
    @FXML
    private ActionController actionSectionController;
    
    @FXML
    private RepetitionController repetitionSectionController;
    

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
        
        Action action = actionSectionController.buildAction();
        
        Repetition repetition = repetitionSectionController.buildRepetition();
        
        
        // Validation of the name
        if (name == null || name.trim().isEmpty()) {
            DialogManager.showWarning("Warning", "Name missing", "Please insert a name for the rule.");
            return; 
        } 
        
        // Validation of the trigger
        if (trigger == null) {
            DialogManager.showWarning("Warning", "Trigger missing", "Please select a trigger before saving the rule.");
            return;
        }
        
        // Validation of the action
        if (action == null) {
            DialogManager.showWarning("Warning", "Action missing", "Please select an action before saving the rule.");
            return;
        }
        
        Rule newRule = new Rule(name, trigger, action, repetition);

        // add the rule to the singleton 
        RuleEngine.getInstance().addRule(newRule);
        
        // debug in console
        System.out.println("Rule Saved: " + name); 
        
        // TO DO
        
        App.setRoot("home");
    }
    
}
