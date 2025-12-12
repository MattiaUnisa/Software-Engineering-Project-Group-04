/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.*;
import java.time.Duration;
import java.time.LocalDateTime;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
 * Unit Test for RuleEngine class (Singleton)
 */
public class RuleEngineTest {

    // Helper classes
    private class MockTrigger implements Trigger {
         private boolean state = false;
        public void setTriggered(boolean value) { this.state = value; }
        @Override
        public boolean isTriggered() { return state; }
    }
    private class MockAction implements Action {
        public int counter = 0;
        @Override
        public void execute(ActionContext context) {
            counter++;
        }
        @Override
        public void stop() {}
    }

    private RuleEngine ruleEngine;
    private MockTrigger trigger;
    private MockAction action;
    private Rule rule;
    
    //toolkit JavaFX initialization
    @BeforeAll
    static void initJfx() {
        new JFXPanel(); 
    }

    //attribute initialization
    @BeforeEach
    void setup() {
        ruleEngine = RuleEngine.getInstance();
        ruleEngine.getRules().clear();

        trigger = new MockTrigger();
        action = new MockAction();

        rule = new Rule("test", trigger, action, new Repetition());
        ruleEngine.addRule(rule);
        
    }


    // Test that getInstance always returns the same object
    @Test
    public void testSingletonInstance() {
        RuleEngine instance1 = RuleEngine.getInstance();
        RuleEngine instance2 = RuleEngine.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2, "RuleEngine should implement Singleton pattern");
    }

    // Test adding a rule to the engine, it should be 2 because a rule is added already in setup
    @Test
    public void testAddRule() {
        Rule rule = new Rule("Rule 1", new MockTrigger(), new MockAction(), new Repetition());

        ruleEngine.addRule(rule);

        assertEquals(2, ruleEngine.getRules().size());
        assertTrue(ruleEngine.getRules().contains(rule));
    }

    // Test deleting a rule from the engine, it should be 1 because a rule is added already in setup
    @Test
    public void testDeleteRule() {
        RuleEngine engine = RuleEngine.getInstance();
        Rule rule = new Rule("Rule to Delete", new MockTrigger(), new MockAction(), new Repetition());
        
        engine.addRule(rule);
        assertEquals(2, engine.getRules().size());

        engine.deleteRule(rule);
        assertEquals(1, engine.getRules().size());
        assertFalse(engine.getRules().contains(rule));
    }
    
    // ---------- ONE TIME EXECUTION ----------
    //the checkAllRules() is called two times in order to simulate the rule behavior
    @Test
    void testOneTimeExecution() throws InterruptedException {
        rule.getRepetition().setOneTime(true);
        rule.getRepetition().setNumRepetition(1);
        trigger.setTriggered(true);

        ruleEngine.CheckAllRules();
        assertEquals(1, rule.getRepetition().getCurrentRepetition());
        ruleEngine.CheckAllRules();
        assertFalse(rule.isActive()); 
    }
    
    // ---------- Limited Repetition ----------
    //the checkAllRules() is called four times in order to simulate the rule behavior, in the first three the currentNumber should increase,
    //at the fourth time is shouldn't increase and isActive should be change in false
    @Test
    void testLimitedRepetitions() {
        rule.getRepetition().setNumRepetition(3);

        trigger.setTriggered(true);

        ruleEngine.CheckAllRules();
        assertEquals(1, rule.getRepetition().getCurrentRepetition());
        assertTrue(rule.isActive());

        rule.getRepetition().setLastExecution(LocalDateTime.now().minusSeconds(5));

        ruleEngine.CheckAllRules();
        assertEquals(2, rule.getRepetition().getCurrentRepetition());
        assertTrue(rule.isActive());

        rule.getRepetition().setLastExecution(LocalDateTime.now().minusSeconds(5));

        ruleEngine.CheckAllRules();
        assertEquals(3, rule.getRepetition().getCurrentRepetition());
        assertTrue(rule.isActive()); 
        
        ruleEngine.CheckAllRules();
        assertEquals(3, rule.getRepetition().getCurrentRepetition());
        assertFalse(rule.isActive());
    }
    
    // ---------- SLEEP PERIOD TEST ----------
    //test of sleep period
    @Test
    void testSleepPeriod() {
        rule.getRepetition().setNumRepetition(5);
        rule.getRepetition().setSleepPeriod(Duration.ofSeconds(10));
        
        trigger.setTriggered(true);

        ruleEngine.CheckAllRules();
        assertEquals(1, rule.getRepetition().getCurrentRepetition());

        ruleEngine.CheckAllRules();
        assertEquals(1, rule.getRepetition().getCurrentRepetition());

        rule.getRepetition().setLastExecution(LocalDateTime.now().minusSeconds(11));

        ruleEngine.CheckAllRules();
        assertEquals(2, rule.getRepetition().getCurrentRepetition()); 
    }
}