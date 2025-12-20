/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.ExternalProgramAction;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author anton
 */
public class ExternalProgramActionController implements ActionControllerInterface{

    @FXML private TextField programPathField;

    public void initialize(){
        
    }
    
    @FXML
    private void onSelectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the program");
        Stage stage = (Stage) programPathField.getScene().getWindow();
        
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){
            programPathField.setText(file.getAbsolutePath());
        }
    }
    
    
    @Override
    public Action buildAction() {
        
        if(programPathField == null || programPathField.getText().isEmpty()){
            return null;
        }
        return ActionFactory.createExternalProgram(new File(programPathField.getText()));
    }
    
    // method to get the instance of the ExternalProgramAction to set the spinner values in the UI
    // called by setAction method in ActionController
    @Override
    public void setActionData(Action action) {
        if (action instanceof ExternalProgramAction) {
            ExternalProgramAction progAction = (ExternalProgramAction) action;
            
            if (progAction.getProgramPath() != null)
                programPathField.setText(progAction.getProgramPath().getAbsolutePath());
        }
    }
    
}
