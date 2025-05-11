package com.event.model;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Event
{
    private final UUID id;
    private String name;
    private String location;
    private String organizer;
    private LocalDateTime date;
    private List<String> categories;
    private List<String> tags;
    private int registrationCount;


    public Event(String name, String location, String organizer, LocalDateTime date, List<String> categories, List<String> tags) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.location = location;
        this.organizer = organizer;
        this.date = date;
        this.categories = categories;
        this.tags = tags;
        this.registrationCount = 0;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
    public String getOrganizer() {
        return organizer;
    }
    public LocalDateTime getDateTime() {
        return date;
    }
    public List<String> getCategories() {
        return categories;
    }
    public List<String> getTags() {
        return tags;
    }
    public int getRegistrationCount() {
        return registrationCount;
    }
    
    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", organizer='" + organizer + '\'' +
                ", date='" + date + '\'' +
                ", categories=" + categories +
                ", tags=" + tags +
                ", registrationCount=" + registrationCount +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date);
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    public void setRegistrationCount(int registrationCount) {
        this.registrationCount = registrationCount;
    }
    public void incrementRegistrationCount() {
        this.registrationCount++;
    }
    public void decrementRegistrationCount() {
         this.registrationCount = registrationCount>0 ? this.registrationCount-1 : this.registrationCount;
    }

}