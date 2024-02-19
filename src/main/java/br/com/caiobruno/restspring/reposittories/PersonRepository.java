package br.com.caiobruno.restspring.reposittories;

import br.com.caiobruno.restspring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

   @Modifying
    @Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id ")
    void disablePerson(@Param("id") Long id);

    @Query("SELECT p FROM Person p  WHERE p.firstName LIKE LOWER(CONCAT('%', :firstName ,'%'))")
    Page<Person> finsPersonsByNames(@Param("firstName") String firstName, Pageable pageable);

}
