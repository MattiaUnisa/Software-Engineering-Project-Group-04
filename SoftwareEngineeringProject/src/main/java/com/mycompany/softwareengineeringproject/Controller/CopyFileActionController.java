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
    private void onSelectFile(){
        FileChooser fileChooserSource = new FileChooser();
        fileChooserSource.setTitle("Select the source file");
        FileChooser fileChooserDest = new FileChooser();
        fileChooserDest.setTitle("Select the destination file");
        
        File fileSource = fileChooserSource.showOpenDialog(null);
        File fileDest = fileChooserDest.showOpenDialog(null);

        if(fileSource != null && fileDest != null){
            sourcePathField.setText(fileSource.getPath());
            destPathField.setText(fileDest.getPath());
        }
    }
    
    @Override
    public Action buildAction() {
            
        if((sourcePathField == null || sourcePathField.getText().isEmpty()) && 
                (destPathField == null || destPathField.getText().isEmpty())){
            return null;
        }
                
        return ActionFactory.createCopyMoveFile(new File(sourcePathField.getText()), new File(destPathField.getText()));
    }
    
}
