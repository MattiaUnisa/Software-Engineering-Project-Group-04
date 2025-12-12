package com.mycompany.softwareengineeringproject.View;
/*
    Two definitions of listener with two methods. BOTH ARE LISTENERS
    setOnAction: 
    from View -> to Model. It listens the actions of the user and set the attribute in the model
    addListener :
    from Model -> to View. When the attribute changes, the graphic component changes
*/
import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
    
    private ChangeListener<Boolean> ruleActiveListener;
    
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
                rule.setActive(activeSwitch.isSelected()); // get the state from the UI 
                System.out.println("User changed rule " + rule.getName() + " to " + rule.isActive()); // debug
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
    
    // logic for click with RESET 
    private void attachSwitchAction() {
        activeSwitch.setOnAction(event -> {
            Rule r = getItem(); // get the current rule
            if (r != null) {
                boolean userWantsActive = activeSwitch.isSelected(); // get user intention (TRUE = ON, FALSE = OFF)

                r.setActive(userWantsActive); // change rule state (ON <-> OFF)
                
                // RESET LOGIC (if user turns ON)
                if (userWantsActive) {
                    if (r.getRepetition() != null) {
                        r.getRepetition().setLastExecution(null); 
                        r.getRepetition().setCurrentRepetition(0);
                        System.out.println("Rule ARMED & RESET");
                    }
                }
                // if the user turns OFF the rule only stops 
                updateSwitchVisuals(userWantsActive);
            }
        });
    }
    
    // method called automatically by the ListView. it updates the single cell
    @Override
    protected void updateItem(Rule rule, boolean empty) {
        // Remove the listener from the old rule (if existed)
        // This can happen when there is a recycle of cells
        // Cell Recycling:            
        // ListView recicle cells, so when we scoll to show other rules the method updateItems is calledd without user interaction, so the buisness logic is involuntarily called.
        if (getItem() != null && ruleActiveListener != null) {
            getItem().activeProperty().removeListener(ruleActiveListener);
        }
        super.updateItem(rule, empty); // super is ListCell, i'm calling this method from ListView

        if (empty || rule == null) {
            // if the rule is empty, don't show it
            setText(null);
            setGraphic(null);
        } else {
            // if there is a rule, set the name and show the layout
            nameLabel.setText(rule.getName());
            
            // In order to avoid bugs when the graphic is updating in scrolling list when there are a lot of rules:
            // Reason: Cell Recycling
            activeSwitch.setOnAction(null); // disable the Listener
            // set the graphic of the switch reading from the model
            activeSwitch.setSelected(rule.isActive()); // button up or down
            updateSwitchVisuals(rule.isActive()); // ON or OFF text and color
            
            attachSwitchAction(); // Enable the logic for reactivation with/without reset

            // Creates the LISTENER         
            // this code runs when RuleEngine change the state from True to False
            ruleActiveListener = (obs, oldVal, newVal) -> {
                // RuleEngine runs on a separated thread, we have to update graphic
                // on JavaFX thread using Platform.runLater
                Platform.runLater(() -> {
                    activeSwitch.setOnAction(null); // disable another time the toggleButton, always to avoid bugs
                    // this change of state by two flows (view -> model and model -> view) could create bugs, so we have to handle it carrefully 
                    activeSwitch.setSelected(newVal);
                    updateSwitchVisuals(newVal);
                    attachSwitchAction();  // enable another time the listener (view -> model)
                });
            };

            // Link the listener to the new rule
            rule.activeProperty().addListener(ruleActiveListener);
            
            // click on the rule -> TO DO (show rule details)
            content.setOnMouseClicked(event -> {
                System.out.println("You clicked on the rule: " + rule.getName());
            });

            setText(null); // disable the toString
            setGraphic(content); // instead of text, draw the content(HBox)
        }
    }
}