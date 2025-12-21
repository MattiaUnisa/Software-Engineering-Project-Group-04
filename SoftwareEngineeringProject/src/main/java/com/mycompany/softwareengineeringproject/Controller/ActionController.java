/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Controller;
import com.mycompany.softwareengineeringproject.Model.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author anton
 */
public class ActionController {
    
    @FXML
    private ComboBox<String> actionComboBox;
    
    @FXML
    private StackPane dynamicContainer;
   
    private ActionControllerInterface action;
    
    //initialize the combobox and add the created actions
    public void initialize() {
        
    //actions are added to the combobox as strings
    actionComboBox.getItems().addAll(
        "Choose Action",
        "PlayAudioAction",
        "NotificationAction",
        "WriteOnFileAction",
        "CopyFileAction",
        "ExternalProgramAction",
        "MoveFileAction",
        "DeleteFileAction"
    );
    
    //the default string selected is Choose Action
    actionComboBox.getSelectionModel().select("Choose Action");  
    
    }
    
    //When the action is selected, fields for entering useful elements are shown through their fmxl files
    @FXML  
    private void onActionSelected() throws IOException {
        
        //We capture the selected trigger as a string
        String selectedAction = actionComboBox.getValue();  
        
        //remove immediately all of the node that compose the StackPane
        dynamicContainer.getChildren().clear(); 
        action = null;
        
        //if there isn't a valid selection we will do nothing
        if (selectedAction == null || selectedAction.equals("Choose Action")) { 
            return; 
        }
        
        //We use the name of the ActionController classes to create an fxmlpath all the time in which a trigger is selected
        //It's important to have the fxmlpath because through it we can use the fxml file of the action in order to instance
        //all of the necessary UI item defined in that file. The result is the parent node that contain the entire UI specified 
        //from the action. Then the loader get the specific Action Controller in order to use his method buildAction
        //At the end we add the entire UI in the StackPane that we have before cleared
        String fxmlPath = "/" + selectedAction + ".fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent actionUI = loader.load();       
        action = loader.getController();
        dynamicContainer.getChildren().add(actionUI);
        
    }
    
    //give the role of creator of the Action Object to the specific controller
    public Action buildAction() {
    
        if (action!= null){
            return action.buildAction();
        }
        return null;
    }
    
    // method called from CreateOrModifyRuleController
    // method that allows us to get the istance of the actions to set values in UI
    // each action has its own setActionData method that is called by this method
    public void setAction(Action existingAction) {
        // get class name
        String actionClassName = existingAction.getClass().getSimpleName();
        
        // get combo box value
        actionComboBox.getSelectionModel().select(actionClassName);
        
        // load FXML and give data
        try {
            String fxmlPath = "/" + actionClassName + ".fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent actionUI = loader.load();
            
            // get specific controller
            action = loader.getController();
            
            // show UI
            dynamicContainer.getChildren().clear();
            dynamicContainer.getChildren().add(actionUI);
            
            // give data to specified controller
            if (action != null) {
                action.setActionData(existingAction);
            }
            
        } catch (IOException e) {
            System.out.println("Error loading UI for Action: " + actionClassName);
        }
    }
}
