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

    @Override
    public String toString() {
        return "Rule: " + "name=" + name + ", trigger=" + trigger + ", action=" + action + ", active=" + active;
    }
    
    
    
}
