package com.mycompany.softwareengineeringproject.View;

import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Lenovo
 */
public class RuleListCell extends ListCell<Rule> {
    private final HBox content;
    private final Label nameLabel;
    private final Button deleteButton;
    private final Pane spacer;
    private final ToggleButton activeSwitch; // Switch for activation state

    public RuleListCell() {
        super();
        nameLabel = new Label();
        nameLabel.getStyleClass().add("rule-name-label");
        
        // -----  SWITCH -----
        activeSwitch = new ToggleButton();
        activeSwitch.getStyleClass().add("toggle-switch"); 
        activeSwitch.setText("OFF"); // Default text
        
        // action when the user clicks the switch
        activeSwitch.setOnAction(event -> { // get the rule associated to this row
            Rule rule = getItem();
            if (rule != null) {
                boolean isActive = activeSwitch.isSelected(); // takes the state of the phisical button in the UI
                rule.setActive(isActive); // updates the attribute in Rule class !!!
                updateSwitchVisuals(isActive); // updates the text ON/OFF
                System.out.println("Rule " + rule.getName() + " is now " + (isActive ? "Active" : "Inactive")); // debug
            }
        });
        
        deleteButton = new Button("🗑"); 
        deleteButton.getStyleClass().add("delete-button");
        
        // the button is positioned on the right of the cell
        spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS); // takes all the horizontal space in the row.
        // thantks to this we have NAME <---> SWITCh BIN 

        content = new HBox(nameLabel, spacer, activeSwitch, deleteButton);
        content.setSpacing(50);  // space between switch and bin
        content.setPadding(new javafx.geometry.Insets(5, 10, 5, 10));
        content.setStyle("-fx-alignment: CENTER_LEFT;");
        
        
        deleteButton.setOnAction(event -> {
            Rule rule = getItem();
            // ObservableList will update automatically
            RuleEngine.getInstance().deleteRule(rule);
            System.out.println("Rule Deleted: " + rule);
        });
    }
    
    // method to change text and color of the switch
    private void updateSwitchVisuals(boolean isActive) {
        if (isActive) {
            activeSwitch.setText("ON");
            // style changed in css
        } else {
            activeSwitch.setText("OFF");
        }
    }

    // method called automatically by the ListView. it updates the single cell
    @Override
    protected void updateItem(Rule rule, boolean empty) {
        super.updateItem(rule, empty); // super is ListCell, i'm calling this method from ListView

        if (empty || rule == null) {
            // if the rule is empty, don't show it
            setText(null);
            setGraphic(null);
        } else {
            // if there is a rule, set the name and show the layout
            nameLabel.setText(rule.getName());
            
            // In order to avoid bugs when the graphic is updating in scrolling list when there are a lot of rules:
            // ListView recicle cells, so when we scoll to show other rules the method updateItems is calledd without user interaction, so the buisness logic is involuntarily called.
            activeSwitch.setOnAction(null); // disable the Listener
            // set the graphic of the switch reading from the model
            activeSwitch.setSelected(rule.isActive()); // button up or down
            updateSwitchVisuals(rule.isActive()); // ON or OFF text
            
            // riactivates the Listener (in the constructor was to define the initial behaviour, now to reactivate it)
            activeSwitch.setOnAction(event -> {
                boolean isActive = activeSwitch.isSelected();
                rule.setActive(isActive);
                updateSwitchVisuals(isActive);
                System.out.println("Rule " + rule.getName() + " is now " + (isActive ? "Active" : "Inactive"));
            });
            
            // click on the rule -> TO DO (show rule details)
            content.setOnMouseClicked(event -> {
                System.out.println("You clicked on the rule: " + rule.getName());
            });

            setText(null); // disable the toString
            setGraphic(content); // instead of text, draw the content(HBox)
        }
    }
}