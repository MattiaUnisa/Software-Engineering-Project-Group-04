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
        @Override
        public boolean isTriggered() { return false; }
    }
    private class MockAction implements Action {
        @Override
        public void execute(ActionContext context) {}
        @Override
        public void stop() {}
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
        Rule rule = new Rule("Rule 1", new MockTrigger(), new MockAction());

        engine.addRule(rule);

        assertEquals(1, engine.getRules().size());
        assertTrue(engine.getRules().contains(rule));
    }

    // Test deleting a rule from the engine
    @Test
    public void testDeleteRule() {
        RuleEngine engine = RuleEngine.getInstance();
        Rule rule = new Rule("Rule to Delete", new MockTrigger(), new MockAction());
        
        engine.addRule(rule);
        assertEquals(1, engine.getRules().size());

        engine.deleteRule(rule);
        assertEquals(0, engine.getRules().size());
        assertFalse(engine.getRules().contains(rule));
    }
}