package ma.ensa.jpaspringdateusecases.repositories;

import ma.ensa.jpaspringdateusecases.entities.Role;
import ma.ensa.jpaspringdateusecases.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
