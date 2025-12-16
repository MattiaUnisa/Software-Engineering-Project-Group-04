/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Trigger;
import com.mycompany.softwareengineeringproject.Model.TriggerFactory;
import java.io.File;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author matda
 */
public class FileTriggerController implements TriggerControllerInterface{
    
    @FXML 
    private TextField filePathField;
    
    @FXML 
    private TextField fileNameField;
    
    //The method initialize is a special callback method of JavaFX. When the fxml file is loaded, the loader search and
    //call automatically this method that in this case is used to give the value to the Spinner
    //A Spinner can't work unless a SpinnerValueFactory is assigned to it
    public void initialize(){
        
   
    }
    
    //This method open a File Chooser that allow at the user to select a file/directory
    @FXML
    private void onSelectDirectory(){
        //Create the object to open the dialog box
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the directory");
        Stage stage = (Stage) filePathField.getScene().getWindow();
        
        //show the box and wait the selection
        File file = directoryChooser.showDialog(stage);
        
        //set the path in the text field
        if(file != null){
            filePathField.setText(file.getAbsolutePath());
        }
    }
    
    //This Method is used to get the value to create the instance of TimeTrigger
    @Override
    public Trigger buildTrigger(){
        String path = filePathField.getText();
        String name = fileNameField.getText();
        return TriggerFactory.createFileTrigger(path,name);
    }
    
}
