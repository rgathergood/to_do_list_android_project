package com.example.rgathergood.project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by rgathergood on 23/03/2018.
 */

public class TestTask {

    Task task;

    @Before
    public void before() {
        task = new Task(1, "Wash the Cat", "Need to buy more shampoo first", "23/03/17", "No");
    }

    @Test
    public void taskHasId() {
        assertEquals(1, task.getId());
    }

    @Test
    public void taskHasName() {
        assertEquals("Wash the Cat", task.getName());
    }

    @Test
    public void taskHasDescription() {
        assertEquals("Need to buy more shampoo first", task.getDescription());
    }

    @Test
    public void taskHasDateAdded() {
        assertEquals("23/03/17", task.getDateAdded());
    }

    @Test
    public void taskIsCompleted() {
        assertEquals("No", task.isCompleted());
    }
}
