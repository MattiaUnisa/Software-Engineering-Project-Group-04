/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.DayOfWeekTrigger;
import com.mycompany.softwareengineeringproject.Model.Trigger;
import com.mycompany.softwareengineeringproject.Model.TriggerFactory;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

/**
 *
 * @author matda
 */
public class DayOfWeekTriggerController implements TriggerControllerInterface{
    
    @FXML
    private DatePicker datePicker;
    
    //The method initialize is a special callback method of JavaFX. When the fxml file is loaded, the loader search and
    //call automatically this method that in this case is used to give a default value to the datepicker
    public void initialize(){
        datePicker.setValue(LocalDate.now());
    }
    
    //This Method is used to get the value to create the instance of DayOfWeekTrigger
    @Override
    public Trigger buildTrigger(){
        DayOfWeek day = datePicker.getValue().getDayOfWeek();
        return TriggerFactory.createDayOfWeekTrigger(day);
    }

    @Override
    public void setTriggerData(Trigger trigger) {
        if (trigger instanceof DayOfWeekTrigger) {
            DayOfWeekTrigger dowTrigger = (DayOfWeekTrigger) trigger;
            DayOfWeek savedDay = dowTrigger.getDay();
            
            // ex. DayOfWeekTrigger contains onlt MONDAY, but DatePicker needs the complete date, 
            //so we take "the first possible monday startin from now"
            LocalDate dateToShow = LocalDate.now().with(TemporalAdjusters.nextOrSame(savedDay));
            datePicker.setValue(dateToShow);
        }
    }
    
}
