package br.com.caiobruno.restspring.services;

import br.com.caiobruno.restspring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
  private final AtomicLong counter = new AtomicLong();
  private Logger logger = Logger.getLogger(PersonServices.class.getName());


  public Person findById(String id){

      logger.info("Finding one person !");

      Person person = new Person();
      person.setId(counter.incrementAndGet());
      person.setFirstName("Caio");
      person.setLastName("Bruno");
      person.setAddress("São Pauo - São Paulo - Brasil");
      person.setGender("Male");
      return person;
  }
  public List<Person> findAll(){
      List<Person> persons = new ArrayList<>();
      for (int i = 0 ; i < 8 ; i++ ){
          Person person = mockPerson(i);
          persons.add(person);
      }
      return persons;
  }

    private Person mockPerson(int i) {


        logger.info("Finding all people !");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name "+ i);
        person.setLastName("Last nmae " + i);
        person.setAddress(" Pais - Estado - Pais " + i);
        person.setGender("Male");
        return person;

    }

    public Person create(Person person){

      logger.info("Create one person !");

      return person;
    }

    public Person update(Person person){

        logger.info("Update one person !");

        return person;
    }

    public void delete(String id){

        logger.info("Delete one person !");

    }

}
