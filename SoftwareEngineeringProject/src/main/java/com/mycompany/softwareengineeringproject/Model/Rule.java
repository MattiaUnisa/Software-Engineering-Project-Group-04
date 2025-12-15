/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
/**
 *
 * @author matda
 */
public class Rule{
    
    private String name;
    private Trigger trigger;
    private Action action;
    private Repetition repetition;

    // we use a BooleanProperty instaed of using a boolean because it notifies the Listeners connected
    // of the change of the state
    // IMPORTANT: so we have to add a Listener (in RuleListCell)
    private final BooleanProperty active;
    
    public Rule(String name, Trigger trigger, Action action,Repetition repetition) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = new SimpleBooleanProperty(true);
        this.repetition = repetition;
    }

    public String getName() {
        return name;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Action getAction() {
        return action;
    }

    // getter of the pure value (boolean)
    public boolean isActive() {
        return active.get();
    }

    // getter of the property (for graphic binding)
    public BooleanProperty activeProperty() {
        return active;
    }

    public Repetition getRepetition() {
        return repetition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public void setRepetition(Repetition repetition) {
        this.repetition = repetition;
    }
    
    //This method permit to rebuild a Rule object from a line of text
    public static Rule parseRules(String line){
        try{
            //Separate the 4 fields of the line considering the same separator used to save the rules in the text file 
            String[] parts = line.split(" \\| ");
            //verify that there are 4 fields
            
            if(parts.length != 5) throw new IllegalArgumentException("Invalid rule format.");
            
            //Here it takes the fields and on everyone use the parsing function
            String name = parts[0];
            boolean active = Boolean.parseBoolean(parts[1]);
            Trigger trigger = TriggerIOFactory.parseTrigger(parts[2]);
            Action action = ActionIOFactory.parseAction(parts[3]);
            Repetition repetition = Repetition.parseRepetition(parts[4]);
            
            if (trigger != null && action != null && repetition != null) {
                Rule rule = new Rule(name, trigger, action ,repetition);
                rule.setActive(active);
                return rule;
            }
        }catch(Exception e){
            System.err.println("ERROR: " + e.getMessage());
        }
        return null;
    }
    
    public String formatRules(Rule rule) {
        String triggerData = this.getTrigger().formatString();
        
        String actionData = this.getAction().formatString();
        
        String repetition = this.getRepetition().formatRepetition();
        
        return rule.getName() + " | " + rule.isActive() + " | " + triggerData + " | " + actionData + " | " + repetition;
    }

    @Override
    public String toString() {
        return "Rule{" + "name=" + name + ", trigger=" + trigger + ", action=" + action + ", active=" + isActive() + ", repetition=" + repetition + '}';
    }
 
    
}
