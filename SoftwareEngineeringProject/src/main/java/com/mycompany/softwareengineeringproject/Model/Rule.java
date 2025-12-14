/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

/**
 *
 * @author matda
 */
public class Rule{
    
    private String name;
    private Trigger trigger;
    private Action action;
    private boolean active;
    private Repetition repetition;

    public Rule(String name, Trigger trigger, Action action, Repetition repetition) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
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

    public boolean isActive() {
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
        this.active = active;
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
            
            if(parts.length != 4) throw new IllegalArgumentException("Invalid rule format.");
            
            //Here it takes the fields and on everyone use the parsing function
            String name = parts[0];
            Trigger trigger = TriggerIOFactory.parseTrigger(parts[1]);
            Action action = ActionIOFactory.parseAction(parts[2]);
            Repetition repetition = Repetition.parseRepetition(parts[3]);
            
            if (trigger != null && action != null && repetition != null) {
                return new Rule(name, trigger, action, repetition); 
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
        
        return rule.getName() + " | " + triggerData + " | " + actionData + " | " + repetition;
    }

    @Override
    public String toString() {
        return "Rule{" + "name=" + name + ", trigger=" + trigger + ", action=" + action + ", active=" + active + ", repetition=" + repetition + '}';
    }
 
    
}
