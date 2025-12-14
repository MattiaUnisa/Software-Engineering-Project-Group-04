/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.softwareengineeringproject.Test;

import com.mycompany.softwareengineeringproject.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test for Rule class
 */
public class RuleTest {

    // Helper classes
    private class MockTrigger implements Trigger {
         private boolean state = false;
        public void setTriggered(boolean value) { this.state = value; }
        @Override
        public boolean isTriggered() { return state; }
        @Override
        public String formatString(){
        return "";
        }
    }
    private class MockAction implements Action {
        public int counter = 0;
        @Override
        public void execute(ActionContext context) {
            counter++;
        }
        @Override
        public void stop() {}
        @Override
        public String formatString(){
        return "";
        }
    }

    private RuleEngine ruleEngine;
    private MockTrigger trigger;
    private MockAction action;
    private Rule rule;

    // Test the correct instantiation of a Rule object
    @Test
    public void testRuleCreation() {
        String name = "Test Rule";
        Trigger trigger = new MockTrigger();
        Action action = new MockAction();

        Rule rule = new Rule(name, trigger, action, new Repetition());

        assertNotNull(rule);
        assertEquals(name, rule.getName());
        assertEquals(trigger, rule.getTrigger());
        assertEquals(action, rule.getAction());
        // Verify that a new rule is active by default
        assertTrue(rule.isActive(), "Rule should be active upon creation");
    }

    // Test the activation and deactivation of a rule
    @Test
    public void testSetActive() {
        Rule rule = new Rule("Test", new MockTrigger(), new MockAction(), new Repetition());

        // Deactivate
        rule.setActive(false);
        assertFalse(rule.isActive());
        System.out.println("Rule deactivated successfully");

        // Reactivate
        rule.setActive(true);
        assertTrue(rule.isActive());
        System.out.println("Rule activated successfully");
    }

    // Test setters for name, trigger, and action
    @Test
    public void testSetters() {
        Rule rule = new Rule("Old Name", new MockTrigger(), new MockAction(), new Repetition());
        
        String newName = "New Name";
        rule.setName(newName);
        
        assertEquals(newName, rule.getName());
    }
}