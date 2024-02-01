package br.com.caiobruno.restspring.controllers;

import br.com.caiobruno.restspring.model.Person;
import br.com.caiobruno.restspring.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @RequestMapping
    public List<Person> findAll(){
        return services.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById (@PathVariable(value = "id") String id ){

     return services.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person){

        return  services.create(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person){

        return  services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public void delete (@PathVariable(value = "id") String id ){

        services.delete(id);
    }
}

//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
