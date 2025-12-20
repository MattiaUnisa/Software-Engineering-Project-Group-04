/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.FileSizeTrigger;
import com.mycompany.softwareengineeringproject.Model.Trigger;
import com.mycompany.softwareengineeringproject.Model.TriggerFactory;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author matda
 */
public class FileSizeTriggerController implements TriggerControllerInterface{
    
    private static final int BYTES_PER_KB = 1024;
    private static final int BYTES_PER_MB = BYTES_PER_KB * 1024;
    private static final int BYTES_PER_GB = BYTES_PER_MB * 1024;
    
    @FXML 
    private TextField filePathField;
    
    @FXML 
    private TextField size;
    
    @FXML
    private ComboBox<String> sizeUnitBox;
    
    //The method initialize is a special callback method of JavaFX. When the fxml file is loaded, the loader search and
    //call automatically this method that in this case is used to give the value to the ComboBox
    public void initialize(){
        
        // Populate the ComboBox with the units of measurement
        sizeUnitBox.getItems().addAll("Bytes", "KB", "MB", "GB");
        
        // Set a default value to prevent errors
        sizeUnitBox.getSelectionModel().select("KB");
        
        // Create a filter in order to accept only numbers
        size.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Regex: accept only number
                return change;
            }
            return null; // Refuse changes if they aren't numeric
        }));
   
    }
    
    //This method open a File Chooser that allow at the user to select a file/directory
    @FXML
    private void onSelectFile(){
        //Create the object to open the dialog box
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file");  
        Stage stage = (Stage) filePathField.getScene().getWindow();
        
        //show the box and wait the selection
        File file = fileChooser.showOpenDialog(stage);
        
        //set the path in the text field
        if(file != null){
            filePathField.setText(file.getAbsolutePath());
        }
    }
    
    //This Method is used to get the value to create the instance of TimeTrigger
    @Override
    public Trigger buildTrigger(){
        String path = filePathField.getText();
        String Size = size.getText();
        int fileSize = Integer.parseInt(Size);
        String selectedUnit = sizeUnitBox.getValue();
        int thresholdInBytes;
        switch (selectedUnit) {
        case "KB":
            thresholdInBytes = fileSize * BYTES_PER_KB;
            break;
        case "MB":
            thresholdInBytes = fileSize * BYTES_PER_MB;
            break;
        case "GB":
            thresholdInBytes = fileSize * BYTES_PER_GB;
            break;
        case "Bytes":
        default:
            thresholdInBytes = fileSize;
            break;
    }
        return TriggerFactory.createFileSizeTrigger(path,thresholdInBytes);
    }

    // method to get the instance of the FileSizeTrigger to set the spinner values in the UI
    // called by setTrigger method in TriggerController
    @Override
    public void setTriggerData(Trigger trigger) {
        if (trigger instanceof FileSizeTrigger) {
            FileSizeTrigger fsTrigger = (FileSizeTrigger) trigger;
            
            filePathField.setText(fsTrigger.getFilePath());
            
            size.setText(String.valueOf(fsTrigger.getSize())); 
            sizeUnitBox.getSelectionModel().select("Bytes");
        }
    }
    
}
