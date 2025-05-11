package com.event.strategy;
import java.util.ArrayList;
import java.util.List;

import com.event.model.Event;

public class NameSearchStrategy implements SearchStrategy
{
    private final String keyword;
    public NameSearchStrategy(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public List<Event> search(List<Event> events, String keyword) {
        List<Event> searchResults = new ArrayList<>();
        for (Event event : events) {
            if (event.getName().toLowerCase().contains(this.keyword)) {
                searchResults.add(event);
            }
        }
        return searchResults;
    }

    @Override
    public boolean matches(Event event) {
        return event.getName().toLowerCase().contains(this.keyword);
    }

    @Override
    public String getName() {
        return this.keyword;
    }
        
    @Override
    public Object getTag() {
        return null;
    }

}