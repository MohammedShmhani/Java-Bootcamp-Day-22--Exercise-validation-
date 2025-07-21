package com.example.eventsystem.Controller;

import com.example.eventsystem.ApiResponse.ApiResponse;
import com.example.eventsystem.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> getEvents() {
        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateEvent(@Valid @RequestBody Event event, @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteEvent(@PathVariable int index) {
        try {

            events.remove(index);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("index invalid");
        }
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
    }

    @PutMapping("/update/{capacity}/{index}")
    public ResponseEntity<?> changeEventCapacity(@PathVariable int index, @PathVariable int capacity) {
        if (capacity < 26) {
            return ResponseEntity.badRequest().body("Capacity must be more than 25");
        }
        if (index < 0 || index >= events.size()) {
            return ResponseEntity.status(400).body("index invalid");
        }

        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Event changed successfully"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> getEventById(@PathVariable String id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return ResponseEntity.status(200).body(event);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Event not found"));
    }


}
