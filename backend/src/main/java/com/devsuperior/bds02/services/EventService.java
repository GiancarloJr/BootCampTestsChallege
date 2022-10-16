package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.repository.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    public void convertDtoToEntity(Event entity,EventDTO dto){
        entity.setCity(cityRepository.getOne(dto.getCityId()));
        entity.setDate(dto.getDate());
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
    }
    public EventDTO update(EventDTO eventDTO) {
        try {
            Optional<Event> obj = eventRepository.findById(eventDTO.getId());
            convertDtoToEntity(obj.get(),eventDTO);
            return new EventDTO(eventRepository.save(obj.get()));
        } catch (NoSuchElementException e){
            throw new ResourceNotFoundException("Entity not found");
        }
    }
}
