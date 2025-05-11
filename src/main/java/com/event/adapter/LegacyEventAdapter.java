package com.event.adapter;

public class LegacyEventAdapter
{
    private String eventName;
    private String eventDate;
    private String eventLocation;

    public LegacyEventAdapter(String eventName, String eventDate, String eventLocation) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
 
    public EventData toEventData() {
        return new EventData(eventName, eventDate, eventLocation);
    }   

    public static LegacyEventAdapter fromEventData(EventData eventData) {
        return new LegacyEventAdapter(eventData.getEventName(), eventData.getEventDate(), eventData.getEventLocation());
    }
   
    public static LegacyEventAdapter fromLegacyEvent(LegacyEvent legacyEvent) {
        return new LegacyEventAdapter(legacyEvent.getEventName(), legacyEvent.getEventDate(), legacyEvent.getEventLocation());
    }
    
    public LegacyEvent toLegacyEvent() {
        return new LegacyEvent(eventName, eventDate, eventLocation);
    }

    public static LegacyEventAdapter copyOf(LegacyEventAdapter adapter) {
        return new LegacyEventAdapter(adapter.eventName, adapter.eventDate, adapter.eventLocation);
    }

}