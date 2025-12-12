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
    private final ObservableList<Rule> rules;
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
                
                //is checked if the current repetition of the rule are less or more of the repetition requested from the user
                if(rule.getRepetition().getCurrentRepetition()>=rule.getRepetition().getNumRepetition()){
                    rule.setActive(false);
                    continue;
                }
            }
        }
    }
    
    public void loadRules(String fileName){
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            if(fileName == null || fileName.isEmpty()){
                return;
            }
            this.rules.clear();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                Rule rule = parseRules(line);
                if(rule != null){
                    this.rules.add(rule);
                }
            }
            System.out.println("Rules successfully loaded from " + fileName + ".");
        }catch(Exception e){
            System.err.println("ERROR loading rules: " + e.getMessage());
        }
    }
    
    public void saveRules(String fileName){
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for (Rule rule : this.rules) {
                String line = formatRuleForSaving(rule);
                writer.println(line);
            }
            System.out.println("Rules successfully saved to " + fileName + ".");
        }catch(Exception e){
            System.err.println("ERROR in the saving of the rules: " + e.getMessage());
        }
    }
    
    
    private String formatRepetition(Repetition repetition){
        return "Repetition:" +
           repetition.isOneTime() + ";" + 
           (repetition.getSleepPeriod() == null ? "null" : repetition.getSleepPeriod()) + ";" + 
           (repetition.getLastExecution() == null ? "null" : repetition.getLastExecution()) + ";" + 
           repetition.getNumRepetition();
    }

    private String formatTrigger(Trigger trigger) {
        if (trigger instanceof TimeTrigger) {
            TimeTrigger tt = (TimeTrigger) trigger;
            // Formato: TimeTrigger:HH:MM
            return "TimeTrigger:" + tt.getTime().getHour() + ":" + tt.getTime().getMinute();
        }
        return "UnknownTrigger:NA"; 
    }

    private String formatAction(Action action) {
        if (action instanceof PlayAudioAction) {
            PlayAudioAction pa = (PlayAudioAction) action;
            return "PlayAudioAction:" + pa.getFilePath();
        }
        return "UnknownAction:NA"; 
    }
    
    private String formatRuleForSaving(Rule rule) {
        String triggerData = formatTrigger(rule.getTrigger());

        String actionData = formatAction(rule.getAction());

        String repetitionData = formatRepetition(rule.getRepetition());

        return rule.getName() + " | " + triggerData + " | " + actionData + " | " + repetitionData;
    }
    
    private Repetition parseRepetition(String repetition){
        if(!repetition.startsWith("Repetition:")){
            return null;
        }
        String dataPart = repetition.substring("Repetition:".length());
        String[] fields = dataPart.split(";");
        
        if (fields.length != 4) throw new IllegalArgumentException("Repetition data error.");
        
        boolean oneTime = Boolean.parseBoolean(fields[0]);
        Duration sleepPeriod = fields[1].equals("null") ? null : Duration.parse(fields[1]);
        LocalDateTime lastExecution = fields[2].equals("null") ? null : LocalDateTime.parse(fields[2]);
        int numRepetition = Integer.parseInt(fields[3]);
        return new Repetition(oneTime, sleepPeriod, lastExecution, numRepetition);
    }
    
    private Trigger parseTrigger(String trigger){
        if(!trigger.startsWith("TimeTrigger:")){
            return null;
        }
        String timePart = trigger.substring("TimeTrigger:".length());
        String[] parts = timePart.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        LocalTime time = LocalTime.of(hour, minute);
        return TriggerFactory.createTimeTrigger(time);
    }
    
    private Action parseAction(String action) {
        if (action.startsWith("PlayAudioAction:")) {
            String path = action.substring("PlayAudioAction:".length());
            return ActionFactory.createPlayAudio(path);
        }
        return null;
    }
    
    private Rule parseRules(String rule){
        try{
            String[] parts = rule.split(" \\| ");
            if(parts.length != 4) throw new IllegalArgumentException("Invalid rule format.");
            
            String name = parts[0];
            Trigger trigger = parseTrigger(parts[1]);
            Action action = parseAction(parts[2]);
            Repetition repetition = parseRepetition(parts[3]);
            
            if (trigger != null && action != null && repetition != null) {
                return new Rule(name, trigger, action, repetition); 
            }
        }catch(Exception e){
            System.err.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    
    @Override
    public String toString() {
        return "RuleEngine{" + "rules=" + rules + '}';
    }
    
    
    
}
