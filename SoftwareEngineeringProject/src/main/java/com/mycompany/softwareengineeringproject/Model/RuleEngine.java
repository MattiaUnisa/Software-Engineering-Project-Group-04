/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
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
    private UiEventListener listener;
    
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
    
    public void setUiEventListener(UiEventListener listener) {
        this.listener = listener;
    }

    public UiEventListener getUiEventListener() {
        return listener;
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
                    //here the RuleEngine give the Uilistener to the Action
                    actioncontext.setUiEventListener(listener);
                    rule.getAction().execute(actioncontext);
                    System.out.println(actioncontext.getExecutionLog());
                });
                
                //is checked if the current repetition of the rule are less or more of the repetition requested from the user
                if(rule.getRepetition().getCurrentRepetition()>=rule.getRepetition().getNumRepetition()){
                    rule.setActive(false);
                    continue;
                }
            }
        }
    }
    
    //Principal method for the loading of the rules from a text file
    public void loadRules(String fileName){
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            if(fileName == null || fileName.isEmpty()){
                return;
            }
            //Here the Observable List is emptied because like this there will be not duplicates
            this.rules.clear();
            //iterate over the text file
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                //Call the parsing function to convert a text string in a Rule object
                Rule rule = Rule.parseRules(line);
                if(rule != null){
                    //if the parsing is happen with good end, the method add the Rule object at the Observable List
                    this.rules.add(rule);
                }
            }
            System.out.println("Rules successfully loaded from " + fileName + ".");
        }catch(Exception e){
            System.err.println("ERROR loading rules: " + e.getMessage());
        }
    }
    
    //Principal method for the saving of the rules in a text file
    public void saveRules(String fileName){
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for (Rule rule : this.rules) {
                //The method convert a Rule Object in a formatted line of text, and write this in the text file
                String line = rule.formatRules(rule);
                writer.println(line);
            }
            System.out.println("Rules successfully saved to " + fileName + ".");
        }catch(Exception e){
            System.err.println("ERROR in the saving of the rules: " + e.getMessage());
        }
    }
    
   
    @Override
    public String toString() {
        return "RuleEngine{" + "rules=" + rules + '}';
    }
    
    
    
}
