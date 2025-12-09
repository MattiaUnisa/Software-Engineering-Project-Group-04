package com.mycompany.softwareengineeringproject.View;

import com.mycompany.softwareengineeringproject.Model.Rule;
import com.mycompany.softwareengineeringproject.Model.RuleEngine;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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

    public RuleListCell() {
        super();
        nameLabel = new Label();
        nameLabel.getStyleClass().add("rule-name-label");
        
        deleteButton = new Button("🗑"); 
        deleteButton.getStyleClass().add("delete-button");
        
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
            System.out.println("Rule Deleted: " + rule);
        });
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
            
            // click on the rule -> TO DO (show rule details)
            content.setOnMouseClicked(event -> {
                System.out.println("You clicked on the rule: " + rule.getName());
            });

            setText(null); // disable the toString
            setGraphic(content); // instead of text, draw the content(HBox)
        }
    }
}