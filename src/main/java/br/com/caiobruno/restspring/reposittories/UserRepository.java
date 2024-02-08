package br.com.caiobruno.restspring.reposittories;

import br.com.caiobruno.restspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT w FROM User WHERE u.userName = useName")
    User findByUserName(@Param("userName") String userName);
}
