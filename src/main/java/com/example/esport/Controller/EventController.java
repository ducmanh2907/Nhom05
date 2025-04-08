package com.example.esport.Controller;

import com.example.esport.Service.EventService;
import com.example.esport.model.Event;

import com.example.esport.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;


    @GetMapping
    public String showEventList(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events/event-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/add-event";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addEvent(@Valid Event event, @RequestParam("imagess") MultipartFile imagess,   BindingResult result) {
        if (result.hasErrors()) {
            return "events/add-event";
        }
        eventService.addEvent(event, imagess);
        return "redirect:/events";
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("event", event);
        return "events/update-event";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, @Valid Event event, @RequestParam("imagess") MultipartFile imagess,
                                BindingResult result) {
        if (result.hasErrors()) {
            event.setId(id); // set id to keep it in the form in case of errorsreturn "/products/update-product";
        }
        eventService.updateEvent(event, imagess);
        return "redirect:/events";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return "redirect:/events";
    }
}
