package com.event.strategy;

import java.util.ArrayList;
import java.util.List;

import com.event.model.Event;

    
public class TagSearchStrategy implements SearchStrategy
{
    private final String tag;

    public TagSearchStrategy(String tag) {
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public boolean matches(Event event) {
        return event.getTags().contains(tag);
    }
   
    @Override
    public List<Event> search(List<Event> events, String keyword) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getTags().contains(keyword)) {
                result.add(event);
            }
        }
        return result;
    }

    @Override
    public String getName() {
        return null;
    }
}