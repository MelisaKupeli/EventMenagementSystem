package com.event.command;

public class Invoker 
{
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set.");
        }
    }
    public void undoCommand() {
        if (command != null) {
            command.undo();
        } else {
            System.out.println("No command set.");
        }
    }
    public void redoCommand() {
        if (command != null) {
            command.redo();
        } else {
            System.out.println("No command set.");
        }
    }
    
    
}