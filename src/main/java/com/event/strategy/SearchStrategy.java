package com.event.strategy;
import java.util.List;

import com.event.model.Event;

public interface  SearchStrategy 
{

    List<Event> search(List<Event> events, String keyword);

    boolean matches(Event event);

    String getName();

    Object getTag();
}