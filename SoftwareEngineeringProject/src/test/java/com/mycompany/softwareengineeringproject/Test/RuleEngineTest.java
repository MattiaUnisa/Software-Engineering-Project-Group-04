/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
/*    
    class FakeTrigger implements Trigger {
        private boolean state = false;
        public void setTriggered(boolean value) { this.state = value; }
        @Override
        public boolean isTriggered() { return state; }
    }

    class FakeAction implements Action {
        public int counter = 0;

        @Override
        public void execute(ActionContext context) {
            counter++;
        }
        @Override
        public void stop() {}
    }*/
    
    private RuleEngine ruleEngine;
    private MockTrigger trigger;
    private MockAction action;
    private Rule rule;

    @BeforeEach
    void setup() {
        ruleEngine = RuleEngine.getInstance();
        ruleEngine.getRules().clear(); // pulizia lista prima di ogni test

        trigger = new MockTrigger();
        action = new MockAction();

        rule = new Rule("test", trigger, action, new Repetition());
        ruleEngine.addRule(rule);
        
    }

    // Clean the engine before each test to ensure a fresh state
    @BeforeEach
    public void clearEngine() {
        RuleEngine.getInstance().getRules().clear();
    }

    // Test that getInstance always returns the same object
    @Test
    public void testSingletonInstance() {
        RuleEngine instance1 = RuleEngine.getInstance();
        RuleEngine instance2 = RuleEngine.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2, "RuleEngine should implement Singleton pattern");
    }

    // Test adding a rule to the engine
    @Test
    public void testAddRule() {
        RuleEngine engine = RuleEngine.getInstance();
        Rule rule = new Rule("Rule 1", new MockTrigger(), new MockAction(), new Repetition());

        engine.addRule(rule);

        assertEquals(1, engine.getRules().size());
        assertTrue(engine.getRules().contains(rule));
    }

    // Test deleting a rule from the engine
    @Test
    public void testDeleteRule() {
        RuleEngine engine = RuleEngine.getInstance();
        Rule rule = new Rule("Rule to Delete", new MockTrigger(), new MockAction(), new Repetition());
        
        engine.addRule(rule);
        assertEquals(1, engine.getRules().size());

        engine.deleteRule(rule);
        assertEquals(0, engine.getRules().size());
        assertFalse(engine.getRules().contains(rule));
    }
    
    // ---------- ONE TIME EXECUTION ----------
    @Test
    void testOneTimeExecution() throws InterruptedException {
        rule.getRepetition().setOneTime(true);
        rule.getRepetition().setNumRepetition(1);
        trigger.setTriggered(true);

        ruleEngine.CheckAllRules();
        System.out.println(rule.getRepetition().getCurrentRepetition());
        assertEquals(1, rule.getRepetition().getCurrentRepetition());
        assertFalse(rule.isActive()); // la regola deve essere disattivata
    }
}