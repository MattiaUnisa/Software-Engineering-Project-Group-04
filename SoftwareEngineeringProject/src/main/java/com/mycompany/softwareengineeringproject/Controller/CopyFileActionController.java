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
        File source = fileChooser.showOpenDialog(null);
        if (source != null) {
            sourcePathField.setText(source.getAbsolutePath());
        }
    }

    @FXML
    private void onSelectDest() {
        javafx.stage.DirectoryChooser dirChooser = new javafx.stage.DirectoryChooser();
        dirChooser.setTitle("Select Destination Directory");
        File dest = dirChooser.showDialog(null);
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
    
}
