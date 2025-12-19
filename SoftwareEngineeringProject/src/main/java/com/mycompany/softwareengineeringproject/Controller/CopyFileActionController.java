/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author anton
 */
public class CopyFileActionController implements ActionControllerInterface{

    @FXML private TextField sourcePathField;
    @FXML private TextField destPathField;
        

    public void initialize(){
        
    }
    
    
    //When the "Select source" button is pressed the program open a window to choose the file that the user want to copy
    @FXML
    private void onSelectSource() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Source File");
        Stage sourcestage = (Stage) sourcePathField.getScene().getWindow();
        File source = fileChooser.showOpenDialog(sourcestage);
        //If the user has select a file, the program write the path in the TextField
        if (source != null) {
            sourcePathField.setText(source.getAbsolutePath());
        }
    }

    //When the "Select destination" button is pressed the program open a window to choose the folder 
    //in which the user want copy the file
    @FXML
    private void onSelectDest() {
        javafx.stage.DirectoryChooser dirChooser = new javafx.stage.DirectoryChooser();
        dirChooser.setTitle("Select Destination Directory");
        Stage deststage = (Stage) destPathField.getScene().getWindow();
        File dest = dirChooser.showDialog(deststage);
        if (dest != null) {
            destPathField.setText(dest.getAbsolutePath());
        }
    }
    
    
    //Create the Action Object starting from the data entered from th user. It is called from the Controller 
    //when the button "Save" is pressed
    @Override
    public Action buildAction() {
            
        if((sourcePathField == null || sourcePathField.getText().isEmpty()) && 
                (destPathField == null || destPathField.getText().isEmpty())){
            return null;
        }
        
        //Delegate at the Factory the construction the CopyFileAction object with the paths of the two object files.
        return ActionFactory.createCopyFile(new File(sourcePathField.getText()), new File(destPathField.getText()));
    }
    
}
