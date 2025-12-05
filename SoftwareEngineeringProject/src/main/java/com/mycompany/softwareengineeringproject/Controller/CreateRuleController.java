/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.*;
import java.time.LocalTime;
import javafx.fxml.FXML;

/**
 *
 * @author Lenovo
 */
public class CreateRuleController {
    
    //initialize the combobox and add the created triggers
    public void initialize() {
    triggerComboBox.getItems().addAll(
        "TimeTrigger"                //Triggers are added to the combobox as strings
    );
}
    
    @FXML  //When the trigger is selected, fields for entering useful elements are shown
    private void onTriggerSelected() {
        String selected = triggerComboBox.getValue();  //I capture the selected trigger as a string
    
        if (selected.equals("TimeTrigger")) {
            hourSpinner.setVisible(true);    //show the hour selector
            minuteSpinner.setVisible(true);  //show the minute selector
        }
    }
    
    @FXML
    private void onCreateRule() {
        //String name = ruleNameField.getText();
        String selectedTrigger = triggerComboBox.getValue();

        Trigger trigger = null;

        if (selectedTrigger.equals("TimeTrigger")) {  //If the string matches, the trigger is fired.
            int hour = hourSpinner.getValue();
            int minute = minuteSpinner.getValue();
            LocalTime time = LocalTime.of(hour, minute);
            trigger = TriggerFactory.createTimeTrigger(time);
        }

        //Rule rule = new Rule(name, trigger);
        //RuleEngine.getInstance().addRule(rule);

        // logica per tornare alla homepage
    }
    
}
