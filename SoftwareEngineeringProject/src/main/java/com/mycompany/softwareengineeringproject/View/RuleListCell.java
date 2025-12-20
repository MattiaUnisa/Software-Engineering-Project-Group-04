package com.mycompany.softwareengineeringproject.View;

import com.mycompany.softwareengineeringproject.Controller.RuleCellController;
import com.mycompany.softwareengineeringproject.Model.Rule;
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
    
    
    // Reference to the Controller
    // since javaFx ListView creates Cells (Views) dynamically via a Factory, the View is instantiated first.
    // It must create its own Controller to delegate logic and establish the connection.
    private final RuleCellController controller;
    
    public RuleListCell() {
        super();
        
        // Init Controller
        this.controller = new RuleCellController(this);
        
        nameLabel = new Label();
        nameLabel.getStyleClass().add("rule-name-label");
        
        // SWITCH
        activeSwitch = new ToggleButton();
        activeSwitch.getStyleClass().add("toggle-switch"); 
        activeSwitch.setText("OFF"); // Default text
        
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
    }
    
    @Override
    protected void updateItem(Rule rule, boolean empty) {
        super.updateItem(rule, empty); // super is ListCell, i'm calling this method from ListView
        
        // Delegate logic to Controller
        controller.onUpdateItem(rule, empty);
    }
    
    //Methods for the Controller to update the View 
    
    public void clearContent() {
        setText(null);
        setGraphic(null);
    }

    public void setContent() {
        setText(null); // disable the toString
        setGraphic(content);
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public ToggleButton getActiveSwitch() {
        return activeSwitch;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public HBox getContainer() {
        return content;
    }
}