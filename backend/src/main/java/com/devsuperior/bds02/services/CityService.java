package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repository.CityRepository;
import com.devsuperior.bds02.services.exceptions.DataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> findAll(){
        List<City> entities = cityRepository.findAll(Sort.by(Sort.Order.asc("name")));
        List<CityDTO> dtos = new ArrayList<>();
        for(City entity: entities){
            CityDTO dto = new CityDTO(entity.getId(),entity.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    public CityDTO insertCity(CityDTO city){
        City obj = new City();
        obj.setName(city.getName());
        return new CityDTO(cityRepository.save(obj));
    }

    public void delete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Entity not found");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integraty violation");
        }
    }
}
