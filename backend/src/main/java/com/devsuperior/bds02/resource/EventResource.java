package com.devsuperior.bds02.resource;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.EventService;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/events")
public class EventResource {

    private EventService eventService;

    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO){
        try{
            eventDTO.setId(id);
            return ResponseEntity.ok().body(eventService.update(eventDTO));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();

        }
    }
}
