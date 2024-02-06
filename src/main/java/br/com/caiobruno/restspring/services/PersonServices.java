package br.com.caiobruno.restspring.services;

import br.com.caiobruno.restspring.controllers.PersonController;
import br.com.caiobruno.restspring.data.vo.v1.PersonVO;
import br.com.caiobruno.restspring.data.vo.v2.PersonVOV2;
import br.com.caiobruno.restspring.exceptions.RequiredObjectIsNullException;
import br.com.caiobruno.restspring.exceptions.ResourceNotFoundException;
import br.com.caiobruno.restspring.mapper.DozerMapper;
import br.com.caiobruno.restspring.mapper.custom.PersonMapper;
import br.com.caiobruno.restspring.model.Person;
import br.com.caiobruno.restspring.reposittories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        List<Person> entityList = repository.findAll();

        var persons =  DozerMapper.parseListObjects(entityList, PersonVO.class);

        persons
                .stream()
                .forEach(p->p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

        return persons;
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;

    }

    public PersonVO create(PersonVO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());


        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person V2!");


        var entity = mapper.convertVoToEntity(person);

        var vo = mapper.convertEntityToVo(repository.save(entity));

        return vo;
    }
}