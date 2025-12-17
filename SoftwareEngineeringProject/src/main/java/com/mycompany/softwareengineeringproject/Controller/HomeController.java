/*
Dependency injection - the linking between controller and view:
1) in FXML file: fx:controller="...HomeController", so when home.fxml is shown, an istance of HomeController is created (done by FXMLLoader)
2) tag = type, id = variables
3) use the @FXML in order that the methon FXMLLoader can inject the object ListView in the variable in the controller class
   also if they are private variables
 */
package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.App;
import com.mycompany.softwareengineeringproject.Model.*;
import com.mycompany.softwareengineeringproject.View.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class HomeController implements Initializable {

    @FXML
    private ListView<Rule> rulesListView;

    @FXML
    private RuleDetailsController ruleDetailsController;

    // We use initialize method instead of canonical constructor 
    // because it's called (automatically by JavaFX) only when the injection of components is completely done
    // constructor could be called before injection -> NullPointerException
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Record an observer into the RuleEngine
        RuleEngine.getInstance().setUiEventListener(new UiEventListener() {
            @Override
            public void onShowNotification(String t, String m) {
                DialogManager.showNotification(t, m);
            }

            @Override
            public void onShowWarning(String t, String h, String c) {
                DialogManager.showWarning(t, h, c);
            }

            @Override
            public void onShowError(String t, String h, String c) {
                DialogManager.showError(t, h, c);
            }

            @Override
            public void onShowAudioPlayer(String filename) {
                DialogManager.showAudioPlayerDialog(filename);
            }
        });

        // Singleton items
        rulesListView.setItems(RuleEngine.getInstance().getRules());

        // Custom Cell Factory
        rulesListView.setCellFactory(param -> new RuleListCell());

        // LISTENER FOR RULE SELECTION
        // When the user selects a rule we pass the control to the controller
        rulesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // We pass the rule at the RuleDetailController
            if (ruleDetailsController != null) {
                ruleDetailsController.setRule(newValue);
            }
        });

        /*
         This is to modify by css the space that the visibility of the details panel 
         creates between it and the list of rules
         */
        if (ruleDetailsController != null) {
            ruleDetailsController.visibleProperty().addListener((obs, wasVisible, isVisible) -> {
                if (isVisible) {
                    // When opened: the panel push the list on the right, so we add a css style class to translate in on the left
                    if (!rulesListView.getStyleClass().contains("list-view-compact")) {
                        rulesListView.getStyleClass().add("list-view-compact");
                    }
                } else {
                    // When closed: remove class -> the list is centered
                    rulesListView.getStyleClass().remove("list-view-compact");
                    
                    // IMPORTANTE: Deseleziona la riga così l'utente può cliccarci di nuovo se vuole riaprirla
                    rulesListView.getSelectionModel().clearSelection();
                }
            });
        }
    }

    @FXML
    private void onNewRuleClick(ActionEvent event) throws IOException {
        App.setRoot("createRule");
    }
}