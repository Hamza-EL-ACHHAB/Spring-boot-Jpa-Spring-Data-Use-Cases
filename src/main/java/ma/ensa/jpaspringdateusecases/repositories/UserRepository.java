package ma.ensa.jpaspringdateusecases.repositories;

import ma.ensa.jpaspringdateusecases.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository /*pour indiquer a Spring qu'il s'agit de la couche DAO*/
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
