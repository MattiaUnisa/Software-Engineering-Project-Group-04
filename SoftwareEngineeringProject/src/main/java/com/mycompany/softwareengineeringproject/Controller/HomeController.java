/*
Dependency injection - the linking between controller and view:
1) in FXML file: fx:controller="...HomeController", so when home.fxml is shown, an istance of HomeController is created (done by FXMLLoader)
2) tag = type, id = variables
3) use the @FXML in order that the methon FXMLLoader can inject the object ListView in the variable in the controller class
   also if they are private variables
 */
package com.mycompany.softwareengineeringproject.Controller;

/**
 *
 * @author Lenovo
 */
import com.mycompany.softwareengineeringproject.App;
import com.mycompany.softwareengineeringproject.Model.*;
import com.mycompany.softwareengineeringproject.View.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class HomeController implements Initializable {

    
    @FXML private ListView<Rule> rulesListView;
    
    // we use initialize method instead of canonical constructor 
    // because it's called (automatically by JavaFX) only when the injection of components is completely done
    // constructor could be called before injection -> NullPointerException
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Record an observer into the RuleEngine
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
        
        if(rulesListView != null){
            rulesListView.setItems(RuleEngine.getInstance().getRules());
            
            rulesListView.setCellFactory(param -> new RuleListCell());
        }
    }

    @FXML
    private void onNewRuleClick() throws IOException {
        App.setRoot("createRule");
    }
}
