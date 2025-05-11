package com.event.strategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.event.model.Event;

public class DateSearchStrategy implements SearchStrategy {
    private LocalDate date;

    public DateSearchStrategy(String nextLine) {
        this.date = LocalDate.parse(nextLine);
    }

    public DateSearchStrategy(LocalDate date) {
        this.date = date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String nextLine) {
        this.date = LocalDate.parse(nextLine);
    }

    public boolean searchEvent(Event event) {
        return event.getDateTime().toLocalDate().equals(date);
    }

    @Override
    public List<Event> search(List<Event> events, String keyword) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getDateTime().toLocalDate().equals(LocalDate.parse(keyword))) {
                result.add(event);
            }
        }
        return result;
    }

    @Override
    public boolean matches(Event event) {
        return event.getDateTime().toLocalDate().equals(date);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getTag() {
        return null;
    }
}
