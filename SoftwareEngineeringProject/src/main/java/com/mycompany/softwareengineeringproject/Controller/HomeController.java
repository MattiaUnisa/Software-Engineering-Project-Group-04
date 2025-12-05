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
import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class HomeController implements Initializable {

    
    @FXML private ListView<Rule> rulesListView;
    
    // we use initialize method instead of canonical constructor 
    // because it's called (automatically by JavaFX) only when the injection of components is completely done
    // constructor could be called before injection -> NullPointerException
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(rulesListView != null){
            rulesListView.setItems(RuleEngine.getInstance().getRules());
            
            rulesListView.setCellFactory(param -> new RuleListCell());
        }
    }

    @FXML
    private void onNewRuleClick() throws IOException {
        App.setRoot("createRule");
    }

    // nested class that defines the single cell for each rule
    private class RuleListCell extends ListCell<Rule> {
        private final HBox content;
        private final Label nameLabel;
        private final Button deleteButton;
        private final Pane spacer;

        public RuleListCell() {
            super();
            nameLabel = new Label();
            nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            
            deleteButton = new Button("🗑"); 
            deleteButton.setStyle("-fx-background-color: #ffcccc; -fx-text-fill: red; -fx-border-color: red; -fx-border-radius: 3;");

            // the button is positioned on the right of the cell
            spacer = new Pane();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            content = new HBox(nameLabel, spacer, deleteButton);
            content.setSpacing(10);
            content.setPadding(new javafx.geometry.Insets(5, 10, 5, 10));
            content.setStyle("-fx-alignment: CENTER_LEFT;");
            
            
            deleteButton.setOnAction(event -> {
                Rule rule = getItem();
                // ObservableList will update automatically
                RuleEngine.getInstance().deleteRule(rule);
            });
        }

        @Override
        protected void updateItem(Rule rule, boolean empty) {
            super.updateItem(rule, empty);

            if (empty || rule == null) {
                // if the rule is empty, don't show it
                setText(null);
                setGraphic(null);
            } else {
                // if there is a rule, set the name and show the layout
                nameLabel.setText(rule.getName());
                
                // click on the rule -> TO DO (show rule details)
                content.setOnMouseClicked(event -> {
                    System.out.println("Hai cliccato sulla regola: " + rule.getName());
                });

                setText(null); // disable the toString
                setGraphic(content); // instead of text, draw the content(HBox)
            }
        }
    }
}
