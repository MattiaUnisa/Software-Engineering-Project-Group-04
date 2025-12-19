/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.DayOfMonthTrigger;
import com.mycompany.softwareengineeringproject.Model.Trigger;
import com.mycompany.softwareengineeringproject.Model.TriggerFactory;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

/**
 *
 * @author matda
 */
public class DayOfMonthTriggerController implements TriggerControllerInterface{
    
    @FXML
    private DatePicker datePicker;
    
    //The method initialize is a special callback method of JavaFX. When the fxml file is loaded, the loader search and
    //call automatically this method that in this case is used to give a default value to the datepicker
    public void initialize(){
        datePicker.setValue(LocalDate.now());
    }
    
    //This Method is used to get the value to create the instance of DayOfMonthTrigger
    @Override
    public Trigger buildTrigger(){
        int day = datePicker.getValue().getDayOfMonth();
        return TriggerFactory.createDayOfMonthTrigger(day);
    }

    @Override
    public void setTriggerData(Trigger trigger) {
        if (trigger instanceof DayOfMonthTrigger) {
            DayOfMonthTrigger domTrigger = (DayOfMonthTrigger) trigger;
            int savedDay = domTrigger.getDay(); 
            
            try {
                LocalDate dateToShow = LocalDate.now().withDayOfMonth(savedDay);
                datePicker.setValue(dateToShow);
            } catch (Exception e) {
                datePicker.setValue(LocalDate.now());
            }
        }
    }
    
}
