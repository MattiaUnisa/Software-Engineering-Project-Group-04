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
        return ActionFactory.createWriteOnFile(path, content);
    }
    
    @Override
    public void setActionData(Action action) {
        if (action instanceof WriteOnFileAction) {
            WriteOnFileAction writeAction = (WriteOnFileAction) action;
            
            filePathField.setText(writeAction.getFilePath());
            message.setText(writeAction.getMessage());
        }
    }
}
