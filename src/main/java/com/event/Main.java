package com.event;

import com.event.command.Command;
import com.event.command.ConcreteCommand;
import com.event.manager.EventManager;
import com.event.model.Event;
import com.event.strategy.DateSearchStrategy;
import com.event.strategy.NameSearchStrategy;
import com.event.strategy.SearchStrategy;
import com.event.strategy.TagSearchStrategy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventManager eventManager = EventManager.getInstance();
    private static final Stack<Command> commandHistory = new Stack<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== EVENT MENAGEMENT SYSTEM ===");
            System.out.println("1. Create Event");
            System.out.println("2. Modify Event");
            System.out.println("3. Search Event");
            System.out.println("4. Register to Event");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createEvent();
                case "2" -> modifyEvent();
                case "3" -> searchEvent();
                case "4" -> registerToEvent();
                case "5" -> {
                    System.out.println("Exiting system.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void createEvent() {
        System.out.println("Enter Event Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Location:");
        String location = scanner.nextLine();

        System.out.println("Enter Organizer:");
        String organizer = scanner.nextLine();

        while(true) {
        System.out.println("Enter Date (YYYY-MM-DD):");
        String date = scanner.nextLine();
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            continue;
        }
        if (LocalDateTime.parse(date + "T00:00").isBefore(LocalDateTime.now())) {
            System.out.println("Event date must be in the future. Please enter a valid date.");
            continue;
        }
        break;
        }

        System.out.println("Enter up to 3 CATEGORİES (one per line):");
        List<String> categories = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String cat = scanner.nextLine();
            if (!cat.isBlank()) categories.add(cat);
        }

        System.out.println("Enter up to 3 TAGS (one per line):");
        List<String> tags = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String tag = scanner.nextLine();
            if (!tag.isBlank()) tags.add(tag);
        }

        Event event = new Event(name, location, organizer, LocalDateTime.now(), categories, tags);
        eventManager.addEvent(event);
        System.out.println("Event created successfully." + event);
    }

    private static void modifyEvent() {
        System.out.println("Enter event name to modify:");
        String name = scanner.nextLine();
        Event event = eventManager.findByName(name);
        if (event == null) {
            System.out.println("Event not found. Please create an event first.");
            return;
        }

        System.out.println("Enter new location (leave blank to skip):");
        String newLocation = scanner.nextLine();
        if (!newLocation.isBlank()) {
            Command cmd = new ConcreteCommand(event, newLocation);
            cmd.execute();
            commandHistory.push(cmd);
        }

        System.out.println("Enter new date (YYYY-MM-DD) (leave blank to skip):");
        String newDate = scanner.nextLine();
        if (!newDate.isBlank()) {
            Command cmd = new ConcreteCommand(event, newDate);
            if (!newDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid date format.");
                return;
            }
            if (LocalDateTime.parse(newDate + "T00:00").isBefore(LocalDateTime.now())) {
                System.out.println("Event date must be in the future.");
                return;
            }
            cmd.execute();
            commandHistory.push(cmd);
        }

        System.out.println("Undo last change? (y/n):");
        String undo = scanner.nextLine();
        if (undo.equalsIgnoreCase("y") && !commandHistory.isEmpty()) {
            commandHistory.pop().undo();
            System.out.println("Last change undone.");
        }
    }

    private static void searchEvent() {
        System.out.println("Search by (1) Name, (2) Tag, (3) Date:");
        String type = scanner.nextLine();
        SearchStrategy strategy ;

        switch (type) {
            case "1" -> {
                System.out.print("Enter name: ");
                strategy = new NameSearchStrategy(scanner.nextLine());
                if (eventManager.findByName(strategy.getName()) == eventManager.findByName("")) {
                    System.out.println("Invalid Event: " + ((NameSearchStrategy) strategy).getName());
                    return;
                }
                return;
            }
            case "2" -> {
                System.out.print("Enter tag: ");
                strategy = new TagSearchStrategy(scanner.nextLine());
                if (eventManager.equals(strategy.getTag())) {
                    System.out.println("Invalid Event: " + ((TagSearchStrategy) strategy).getTag());
                    return;
                }
            }
            case "3" -> {
                System.out.print("Enter date (YYYY-MM-DD): ");
                String inputDate= scanner.nextLine();
                if (!inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                 System.out.println("Invalid date format.");
                return;
            }
                if (LocalDateTime.parse(inputDate + "T00:00").isBefore(LocalDateTime.now())) {
                System.out.println("Event date must be in the future.");
                return;
                }
                strategy = new DateSearchStrategy(inputDate);

            }
            default -> {
                System.out.println("Invalid search type.");
                return;
            }
        }

        List<Event> results = eventManager.search(strategy);
        if (results.isEmpty()) {
            System.out.println("No events found.");
        } else {
            for (Event e : results) {
                System.out.println(e);
            }
        }
    }

    private static void registerToEvent() {
        System.out.println("Enter event name to register:");
        String name = scanner.nextLine();
        Event event = eventManager.findByName(name);
        if (event == null) {
            System.out.println("Event not found.");
            return;
        }

        System.out.println("1. Register\n2. Cancel registration");
        String action = scanner.nextLine();
        switch (action) {
            case "1" -> {
                eventManager.registerToEvent(event.getId());
                event.incrementRegistrationCount();
                eventManager.addEvent(event);
                commandHistory.push(new ConcreteCommand(event, event.getLocation()));
                System.out.println("Successfully registered.");
            }
            case "2" -> {
                eventManager.cancelRegistration(event);
                System.out.println("Registration canceled.");
            }
            default -> System.out.println("Invalid action.");
        }
    }
}

