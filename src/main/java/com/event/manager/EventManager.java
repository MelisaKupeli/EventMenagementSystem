package com.event.manager;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.event.model.Event;
import com.event.strategy.SearchStrategy;

public class EventManager 
{
    private static EventManager instance;
    private final List<Event> events;

    private EventManager() {
        this.events = new ArrayList<>();
    }

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(UUID id) {
        events.removeIf(event -> event.getId().equals(id));
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public Event getEventById(UUID id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    public Event findByName(String name) {
        for (Event e : events) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
            }
        return null;
    }

    public void cancelRegistration(Event event) {
        for (Event e : events) {
            if (e.getId().equals(event.getId())) {
                e.decrementRegistrationCount();
                System.out.println("Registration cancelled.");
                return;
            }
        }
        System.out.println("Event not found.");
    }

    public void registerToEvent(UUID eventId) {
        Event e = getEventById(eventId);
        if (e != null) {
            e.incrementRegistrationCount();
            System.out.println("Registration successful.");
        } else {
            System.out.println("Event not found.");
        }
    }

    public List<Event> search(SearchStrategy strategy) {
        List<Event> results = new ArrayList<>();
        for (Event event : events) {
            if (strategy.matches(event)) {
                results.add(event);
            }
        }
        return results;
    }

}