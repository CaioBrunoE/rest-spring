package br.com.caiobruno.restspring.controllers;


import br.com.caiobruno.restspring.data.vo.v1.PersonVO;
import br.com.caiobruno.restspring.data.vo.v2.PersonVOV2;
import br.com.caiobruno.restspring.services.PersonServices;
import br.com.caiobruno.restspring.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person/v1")
public class PersonController {



    @Autowired
    private PersonServices service;

    @GetMapping(produces = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML})
    public List<PersonVO> findAll() {

        List<PersonVO> listPerson = service.findAll();

        return listPerson;
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML})
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(
            consumes = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML},
            produces = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML})
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML},
            produces = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return service.createV2(person);
    }


    @PutMapping(
            consumes = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML},
            produces = {MediaType.APLICATION_JSON, MediaType.APLICATION_XML,MediaType.APLICATION_YML})
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}