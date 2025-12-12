/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.time.LocalDateTime;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author matda
 */
public class RuleEngine {
    private static RuleEngine instance;
    private ObservableList<Rule> rules;
    // observer: the list updates automatically the view 
    
    private RuleEngine() {
        this.rules = FXCollections.observableArrayList();
    }
    
    // Singleton
    public static RuleEngine getInstance() {
        if (instance == null) {
            instance = new RuleEngine();
        }
        return instance;
    }
    
    public void addRule(Rule rule) {
        this.rules.add(rule);
    }
    
    public void deleteRule(Rule rule){
        this.rules.remove(rule);
    }

    public ObservableList<Rule> getRules(){
        return this.rules;
    }
    
    public void CheckAllRules(){
        for(Rule rule : rules){
            if(rule.getTrigger().isTriggered() && rule.isActive()){
                LocalDateTime nextFireTime = null;
                
                //is checked if the rule is already executed and in case is decided to execute it one time, 
                //the boolean attribute active is set to false
                if(rule.getRepetition().isOneTime() && rule.getRepetition().getLastExecution()!=null){
                    rule.setActive(false);
                    continue;
                }
                
                //is checked if the rule is already executed and if the user insert a sleep period.
                //In this case the next Time in which the rule should be executed is calculated
                //and so is checked if the current Time isBefore or after the time calculated. 
                //In case of isBefore through continue instruction the rest of the for is ignored
                if(rule.getRepetition().getLastExecution()!=null && rule.getRepetition().getSleepPeriod()!=null){
                    nextFireTime = rule.getRepetition().getLastExecution().plus(rule.getRepetition().getSleepPeriod());
                    if (LocalDateTime.now().isBefore(nextFireTime)) {
                        continue;
                    }
                }
                
                //is checked if the current repetition of the rule are less or more of the repetition requested from the user
                if(rule.getRepetition().getCurrentRepetition()>=rule.getRepetition().getNumRepetition()){
                    rule.setActive(false);
                    continue;
                }
                
                // the parameters of ripetition (LastExecution e CurrentRepetition) are updated here immediately 
                // to block multiple executions (race condition) in the fast iteration of the rule engine.
                rule.getRepetition().setLastExecution(LocalDateTime.now());
                int i = rule.getRepetition().getCurrentRepetition();
                i++;
                rule.getRepetition().setCurrentRepetition(i);
                
                // Wrap the execution in Platform.runLater in the way that the RuleEngineThread verify if the trigger of a rule is triggered and
                // then the application thread execute it. So the application thread when is free show it on display, while the RuleEngineThread 
                // continues to check triggers
                Platform.runLater(() -> {
                    ActionContext actioncontext = new ActionContext();
                    rule.getAction().execute(actioncontext);
                    System.out.println(actioncontext.getExecutionLog());
                });
            }
        }
    }
    
    @Override
    public String toString() {
        return "RuleEngine{" + "rules=" + rules + '}';
    }
    
    
    
}
