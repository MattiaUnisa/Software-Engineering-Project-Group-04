/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.CopyFileAction;
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
    
    @FXML
    private void onSelectSource() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Source File");
        Stage sourcestage = (Stage) sourcePathField.getScene().getWindow();
        File source = fileChooser.showOpenDialog(sourcestage);
        if (source != null) {
            sourcePathField.setText(source.getAbsolutePath());
        }
    }

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
    
    @Override
    public Action buildAction() {
            
        if((sourcePathField == null || sourcePathField.getText().isEmpty()) && 
                (destPathField == null || destPathField.getText().isEmpty())){
            return null;
        }
                
        return ActionFactory.createCopyFile(new File(sourcePathField.getText()), new File(destPathField.getText()));
    }
    
    @Override
    public void setActionData(Action action) {
        if (action instanceof CopyFileAction) {
            CopyFileAction copyAction = (CopyFileAction) action;
            
            if (copyAction.getSourcePath() != null)
                sourcePathField.setText(copyAction.getSourcePath().getAbsolutePath());
            
            if (copyAction.getDestPath() != null)
                destPathField.setText(copyAction.getDestPath().getAbsolutePath());
        }
    }
    
}
