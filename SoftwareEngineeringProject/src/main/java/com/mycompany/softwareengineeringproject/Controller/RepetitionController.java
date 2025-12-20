/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Repetition;
import java.time.Duration;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author matda
 */
public class RepetitionController {
    
    // Reference to FXML    
    @FXML private RadioButton yesRadio;
    
    @FXML private RadioButton noRadio;
    
    @FXML 
    private VBox repetitionInputContainer;

    // Spinner for the Sleep Period
    @FXML 
    private Spinner<Integer> daySpinner;
    
    @FXML 
    private Spinner<Integer> hourSpinner;
    
    @FXML 
    private Spinner<Integer> minuteSpinner;
    
    @FXML 
    private Spinner<Integer> secondSpinner;
    
    @FXML 
    private Spinner<Integer> repetition; 
    
    private boolean isOneTime = true;
    
    @FXML
    public void initialize() {
        // hide the content when the app is started
        repetitionInputContainer.setVisible(false);
        repetitionInputContainer.setManaged(false);
        
        // Spinnner Initialization

        // Sleep Period: Days (Min: 0, Max: 365, Default: 0)
        daySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 365, 0));
        
        // Sleep Period: Hours (Min: 0, Max: 23, Default: 0)
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        
        // Sleep Period: Minutes (Min: 0, Max: 59, Default: 0)
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        
        // Sleep Period: Seconds (Min: 0, Max: 59, Default: 0)
        secondSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));
        
        // Max Repetitions: (Min: 1, Max: 50, Default: 1)
        repetition.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 1));
    }
    
    //if the RadioButton Yes is clicked, this method is used
    @FXML
    private void handleYesSelected() {
        isOneTime = false;
        repetitionInputContainer.setVisible(true);
        repetitionInputContainer.setManaged(true);
    }
    
    //if the RadioButton No is clicked, this method is used
    @FXML
    private void handleNoSelected() {
        isOneTime = true;
        repetitionInputContainer.setVisible(false);
        repetitionInputContainer.setManaged(false);
    }
    
    //This is the method used to create the Repetition Object
    public Repetition buildRepetition(){
        long days = daySpinner.getValue();
        long hours = hourSpinner.getValue();
        long minutes = minuteSpinner.getValue();
        long seconds = secondSpinner.getValue();
        Duration sleepPeriod = null;
        if (days > 0 || hours > 0 || minutes > 0 || seconds > 0) {
            sleepPeriod = Duration.ofDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
        }
        int numRepetitions = repetition.getValue();
        Repetition repetition = new Repetition(isOneTime, sleepPeriod, null, numRepetitions);
        return repetition;
    }
    
    // method to set fields of UI starting from an existing Repetition.
    public void setRepetition(Repetition existingRepetition) {
        // radioButton settings (YES/NO)
        if (existingRepetition.isOneTime()) {
            noRadio.setSelected(true);
            handleNoSelected(); // hide controls
        } else {
            yesRadio.setSelected(true);
            handleYesSelected(); // show controls
            
            // set duration spinner
            Duration duration = existingRepetition.getSleepPeriod();
            if (duration != null) {
                // break down Duration Days, Hours, Minutes, Seconds
                long days = duration.toDays();
                long hours = duration.toHours() % 24;
                long minutes = duration.toMinutes() % 60;
                long seconds = duration.getSeconds() % 60;
                
                daySpinner.getValueFactory().setValue((int) days);
                hourSpinner.getValueFactory().setValue((int) hours);
                minuteSpinner.getValueFactory().setValue((int) minutes);
                secondSpinner.getValueFactory().setValue((int) seconds);
            }
            
            // set repetitions number
            // it must be >= 1
            int numReps = existingRepetition.getNumRepetition();
            if (numReps < 1) numReps = 1; 
            repetition.getValueFactory().setValue(numReps);
        }
    }
    
}
