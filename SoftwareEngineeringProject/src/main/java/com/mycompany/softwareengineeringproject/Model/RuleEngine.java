/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Model;

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
    
    public void deleateRule(Rule rule){
        this.rules.remove(rule);
    }

    public ObservableList<Rule> getRules(){
        return this.rules;
    }
    @Override
    public String toString() {
        return "RuleEngine{" + "rules=" + rules + '}';
    }
    
    
    
}
