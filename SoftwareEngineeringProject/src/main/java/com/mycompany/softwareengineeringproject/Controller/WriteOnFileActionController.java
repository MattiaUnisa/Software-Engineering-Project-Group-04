/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.WriteOnFileAction;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author anton
 */
public class WriteOnFileActionController implements ActionControllerInterface{
    
    @FXML private TextField message;
    @FXML private TextField filePathField;
    
    public void initialize(){
        
    }
    
    //When the "Select file" button is pressed the program open a window to choose the file in which the user want write
    @FXML
    private void onSelectFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the text file");
        Stage stage = (Stage) filePathField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){
            filePathField.setText(file.getAbsolutePath());
        }
    }
    
    //Create the Action Object starting from the data entered from the user. It is called from the Controller 
    //when the button "Save" is pressed
    @Override
    public Action buildAction(){
        String path = filePathField.getText();
        String content = message.getText();
        if(content == null || content.isEmpty()){
            return null;
        }
        if(path == null || path.isEmpty()){
            return null;
        }
        //Delegate at the Factory the construction the WriteOnFileAction object
        return ActionFactory.createWriteOnFile(path, content);
    }
    
    // method to get the instance of the WriteOnFileAction to set the spinner values in the UI
    // called by setAction method in ActionController
    @Override
    public void setActionData(Action action) {
        if (action instanceof WriteOnFileAction) {
            WriteOnFileAction writeAction = (WriteOnFileAction) action;
            
            filePathField.setText(writeAction.getFilePath());
            message.setText(writeAction.getMessage());
        }
    }
}
