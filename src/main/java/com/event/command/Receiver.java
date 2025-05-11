package com.event.command;

public class Receiver {
    private final String name;

    public Receiver(String name) {
        this.name = name;
    }

    public void performAction(String action) {
        System.out.println(name + " is performing action: " + action);
    }
}
