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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateOrModifyRuleController {
    
    // takes the name from the tag in fxml
    @FXML 
    private TextField nameField;
    
    @FXML private Label titleLabel;
    
    @FXML
    private TriggerController triggerSectionController;
    
    @FXML
    private ActionController actionSectionController;
    
    @FXML
    private RepetitionController repetitionSectionController;
    
    // variable to track the rule we are modifing
    // if is null, we are in "creation mode". If it's not null, we are in "modify mode".
    private Rule ruleToEdit = null;
    
    // method called by Rule DetailsController to share data of the rule to modify
    public void initData(Rule rule) {
        this.ruleToEdit = rule;
        
        // change title
        titleLabel.setText("Modify Rule: " + rule.getName());
        
        // set name
        nameField.setText(rule.getName());
        
        // Set values sor each section
        triggerSectionController.setTrigger(rule.getTrigger());
        actionSectionController.setAction(rule.getAction());
        repetitionSectionController.setRepetition(rule.getRepetition());
        
        System.out.println("Editing mode active for: " + rule.getName());
    }
    
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

        // LOGICA SALVATAGGIO:
        if (ruleToEdit != null) {
            // --- MODIFY ---
            // remove old rule and add a new one
            RuleEngine.getInstance().getRules().remove(ruleToEdit);
            RuleEngine.getInstance().addRule(newRule);
            System.out.println("Rule Modified: " + ruleToEdit.getName() + " -> " + name);
        } else {
            // --- CREATION ---
            RuleEngine.getInstance().addRule(newRule);
            System.out.println("Rule Created: " + name); 
        } 
        
        App.setRoot("home");
    }
    
}
