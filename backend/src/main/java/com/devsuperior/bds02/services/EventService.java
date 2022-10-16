package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<EventDTO> findAll(){
        List<Event> entities = eventRepository.findAll();
        List<EventDTO> dtos = new ArrayList<>();
        EventDTO dto = new EventDTO();
        for(Event entity: entities){
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDate(entity.getDate());
            dto.setUrl(entity.getUrl());
            dto.setCityId(entity.getCity().getId());
            dtos.add(dto);
        }
        return dtos;
    }

    public EventDTO convertyEntityToDto(Event entity){
        EventDTO dto = new EventDTO(entity.getId(),entity.getName(),entity.getDate(), entity.getUrl(), entity.getCity().getId());
        return dto;
    }

}
