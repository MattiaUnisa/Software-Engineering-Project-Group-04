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
    
    //inizializzo la combobox aggiungengo i trigger creati
    public void initialize() {
    triggerComboBox.getItems().addAll(
        "TimeTrigger"                //i trigger vengono aggiunti nella combobox semplicemente come delle stringhe
    );
}
    
    @FXML  //Quando il trigger viene selezionato, vengono mostrati i campi per inserire gli elementi utili
    private void onTriggerSelected() {
        String selected = triggerComboBox.getValue();  //catturo il trigger selezionato sottoforma di stringa
    
        if (selected.equals("TimeTrigger")) {
            hourSpinner.setVisible(true);    //mostro il selettore delle ore
            minuteSpinner.setVisible(true);  //mostro il selettore dei minuti
        }
    }
    
    @FXML
    private void onCreateRule() {
        //String name = ruleNameField.getText();
        String selectedTrigger = triggerComboBox.getValue();

        Trigger trigger = null;

        if (selectedTrigger.equals("TimeTrigger")) {  //Se la stringa corrisponde viene generato il trigger
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
