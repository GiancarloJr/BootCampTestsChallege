package com.devsuperior.bds02.resource;


import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import com.devsuperior.bds02.services.exceptions.DataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityResource {

    @Autowired
    private CityService cityService;

    @GetMapping()
    private ResponseEntity<List<CityDTO>> findAllCity(){
        List<CityDTO> dto = cityService.findAll();
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    private ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO city){
        return ResponseEntity.status(HttpStatus.valueOf(201)).body(cityService.insertCity(city));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            cityService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (DataBaseException e){
            return ResponseEntity.badRequest().build();
        }

    }

}
