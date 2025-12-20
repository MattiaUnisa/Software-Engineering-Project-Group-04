/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.TimeTrigger;
import com.mycompany.softwareengineeringproject.Model.Trigger;
import com.mycompany.softwareengineeringproject.Model.TriggerFactory;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 *
 * @author matda
 */
public class TimeTriggerController implements TriggerControllerInterface{
    
    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;
    
    //The method initialize is a special callback method of JavaFX. When the fxml file is loaded, the loader search and
    //call automatically this method that in this case is used to give the value to the Spinner
    //A Spinner can't work unless a SpinnerValueFactory is assigned to it
    public void initialize(){
        
    hourSpinner.setValueFactory(    //Function used to set the value of the Spinner
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0)  //Object that manages values in the Spinner, the first is the min value, the second is the max value and the third is the first value
    );

    minuteSpinner.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0)
    );
    }
    
    //This Method is used to get the value to create the instance of TimeTrigger
    @Override
    public Trigger buildTrigger(){
        int hour = hourSpinner.getValue();  //take the int hour value
        int minute = minuteSpinner.getValue(); //take the int minute value
        LocalTime time = LocalTime.of(hour, minute); //create a variable that we use to create the TimeTrigger
        return TriggerFactory.createTimeTrigger(time);
    }
    
    // method to get the instance of the TimeTrigger to set the spinner values in the UI
    @Override
    public void setTriggerData(Trigger trigger) {
        if (trigger instanceof TimeTrigger) {
            TimeTrigger timeTrigger = (TimeTrigger) trigger;
            LocalTime time = timeTrigger.getTime();             
            
            hourSpinner.getValueFactory().setValue(time.getHour());
            minuteSpinner.getValueFactory().setValue(time.getMinute());
        }
    }
    
}
