package com.example.esport.Service;

import com.example.esport.model.Event;
import com.example.esport.model.Product;
import com.example.esport.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {
    private final  EventRepository eventRepository;
    // Retrieve all products from the database
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }
    // Add a new product to the database
    public Event addEvent(Event event, MultipartFile imagess) {
        try {
            if (imagess != null && !imagess.isEmpty()) {
                String kame = saveKameStatic(imagess);
                event.setKame(kame);
            }
            return eventRepository.save(event);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    private String saveKameStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }
    // Update an existing product
    public Event updateEvent(@NotNull Event event, MultipartFile imagess) {
        Event existingEvent = eventRepository.findById(event.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        event.getId() + " does not exist."));
        existingEvent.setEventName(event.getEventName());
        existingEvent.setCore(event.getCore());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setLocation(event.getLocation());
        try {
            if (imagess != null && !imagess.isEmpty()) {
                String kame = saveKameStatic(imagess);
                existingEvent.setKame(kame);
            }


        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return eventRepository.save(existingEvent);
    }
    // Delete a product by its id
    public void deleteEventById(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        eventRepository.deleteById(id);
    }
}