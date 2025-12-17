package com.mycompany.softwareengineeringproject.Controller;

import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import com.mycompany.softwareengineeringproject.View.RuleListCell;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;

/*
    Two definitions of listener with two methods. BOTH ARE LISTENERS
    setOnAction: 
    from View -> to Model. It listens the actions of the user and set the attribute in the model
    addListener :
    from Model -> to View. When the attribute changes, the graphic component changes
*/

public class RuleCellController {

    private final RuleListCell view;
    private Rule currentRule;
    private ChangeListener<Boolean> ruleActiveListener;

    public RuleCellController(RuleListCell view) {
        this.view = view;
    }

    // method called automatically by the ListView (delegated from View). it updates the single cell
    public void onUpdateItem(Rule rule, boolean empty) {
        // Remove the listener from the old rule (if existed)
        // This can happen when there is a recycle of cells
        // Cell Recycling:            
        // ListView recicle cells, so when we scoll to show other rules the method updateItems is calledd without user interaction, so the buisness logic is involuntarily called.
        if (currentRule != null && ruleActiveListener != null) {
            currentRule.activeProperty().removeListener(ruleActiveListener);
        }

        this.currentRule = rule;

        if (empty || rule == null) {
            // if the rule is empty, don't show it
            view.clearContent();
        } else {
            // if there is a rule, set the name and show the layout
            view.getNameLabel().setText(rule.getName());

            // In order to avoid bugs when the graphic is updating in scrolling list when there are a lot of rules:
            // Reason: Cell Recycling
            view.getActiveSwitch().setOnAction(null); // disable the Listener
            // set the graphic of the switch reading from the model
            view.getActiveSwitch().setSelected(rule.isActive()); // button up or down
            updateSwitchVisuals(rule.isActive()); // ON or OFF text and color

            attachSwitchAction(); // Enable the logic for reactivation with/without reset
            attachDeleteAction(); // Enable delete logic

            // Creates the LISTENER          
            // this code runs when RuleEngine change the state from True to False
            ruleActiveListener = (obs, oldVal, newVal) -> {
                // RuleEngine runs on a separated thread, we have to update graphic
                // on JavaFX thread using Platform.runLater
                Platform.runLater(() -> {
                    view.getActiveSwitch().setOnAction(null); // disable another time the toggleButton, always to avoid bugs
                    // this change of state by two flows (view -> model and model -> view) could create bugs, so we have to handle it carrefully 
                    view.getActiveSwitch().setSelected(newVal);
                    updateSwitchVisuals(newVal);
                    attachSwitchAction();  // enable another time the listener (view -> model)
                });
            };

            // Link the listener to the new rule
            rule.activeProperty().addListener(ruleActiveListener);

            // click on the rule -> TO DO (show rule details)
            // (Handled by ListView SelectionModel in Home, but keeping this as requested)
            view.getContainer().setOnMouseClicked(event -> {
                System.out.println("You clicked on the rule: " + rule.getName());
            });

            view.setContent(); // instead of text, draw the content(HBox)
        }
    }

    // logic for click with RESET 
    private void attachSwitchAction() {
        // action when the user clicks the switch
        view.getActiveSwitch().setOnAction(event -> {
            Rule r = currentRule; // get the current rule
            if (r != null) {
                boolean userWantsActive = view.getActiveSwitch().isSelected(); // get user intention (TRUE = ON, FALSE = OFF)

                r.setActive(userWantsActive); // change rule state (ON <-> OFF)
                System.out.println("User changed rule " + r.getName() + " to " + r.isActive()); // debug
                
                // RESET LOGIC (if user turns ON)
                if (userWantsActive) {
                    if (r.getRepetition() != null) {
                        r.getRepetition().setLastExecution(null); 
                        r.getRepetition().setCurrentRepetition(0);
                        System.out.println("Rule RESETTED");
                    }
                }
                // if the user turns OFF the rule only stops 
                updateSwitchVisuals(userWantsActive);
            }
        });
    }

    private void attachDeleteAction() {
        view.getDeleteButton().setOnAction(event -> {
            Rule rule = currentRule;
            if (rule != null) {
                // ObservableList will update automatically
                RuleEngine.getInstance().deleteRule(rule);
                System.out.println("Rule Deleted: " + rule);
            }
        });
    }

    // method to change text and color of the switch
    private void updateSwitchVisuals(boolean isActive) {
        if (isActive) {
            view.getActiveSwitch().setText("ON");
            // style changed in css
        } else {
            view.getActiveSwitch().setText("OFF");
        }
    }
}