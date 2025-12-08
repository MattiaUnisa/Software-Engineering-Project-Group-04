/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Action;
import com.mycompany.softwareengineeringproject.Model.ActionFactory;
import com.mycompany.softwareengineeringproject.Model.ActionContext;
import com.mycompany.softwareengineeringproject.Model.PlayAudioAction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
/**
 *
 * @author anton
 */
public class AudioController implements ActionControllerInterface{
    
    @FXML private Button stopButton;
    @FXML private TextField filePathField;
    
    private PlayAudioAction currentTestInstance;
    
    //This method open a File Chooser that allow at the user to select a file audio
    @FXML
    private void onSelectFile(){
        //Create the object to open the dialog box
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file audio");
        
        //show the box and wait the selection
        File file = fileChooser.showOpenDialog(null);
        
        //set the path in the text field
        if(file != null){
            filePathField.setText(file.getAbsolutePath());
        }
    }
    
    
    //This method is used to create the Action object after the user has finished configuring it, 
    //making it ready to be saved in the Rule
    @Override
    public Action buildAction(){
        String path = filePathField.getText();
        if(path == null || path.isEmpty()){
            return null;
        }
        return ActionFactory.createPlayAudio(path);
    }
}
