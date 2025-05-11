package com.event.command;

import com.event.model.Event;

public class ConcreteCommand implements Command
{
    private final Event event;
    private final String newLocation;
    private String previousLocation;

    public ConcreteCommand( Event event, String newLocation) {
        this.event = event;
        this.newLocation = newLocation;    
    }

    @Override
    public void execute() {
        previousLocation = event.getLocation();
        event.setLocation(newLocation);
        System.out.println("Location changed from " + previousLocation + " to " + newLocation);
    }
    @Override
    public void undo() {
        System.out.println("Undoing command on receiver: " + event.getName());
        event.setLocation(previousLocation);
        System.out.println("Location reverted to " + previousLocation);
        
    }
    @Override
    public void redo() {
        System.out.println("Redoing command on receiver: " + event.getName());
        event.setLocation(newLocation);
        System.out.println("Location changed from " + previousLocation + " to " + newLocation);
    }
    @Override
    public String getCommand() {
        return "Change location of event " + event.getName() + " from " + previousLocation + " to " + newLocation;
    }

    @Override
    public String getDescription() {
        return "Change location of event " + event.getName() + " from " + previousLocation + " to " + newLocation;
    }
    @Override
    public String toString() {
        return "ConcreteCommand{" +
                "event=" + event +
                ", newLocation='" + newLocation + '\'' +
                ", previousLocation='" + previousLocation + '\'' +
                '}';
    }

}