package com.event.command;

public interface Command 
{
    void execute();
    void undo();
    void redo();
    
    String getCommand();
    String getDescription();
    
    @Override
    String toString();
}