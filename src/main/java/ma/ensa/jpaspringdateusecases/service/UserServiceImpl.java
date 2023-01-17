package ma.ensa.jpaspringdateusecases.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.ensa.jpaspringdateusecases.entities.Role;
import ma.ensa.jpaspringdateusecases.entities.User;
import ma.ensa.jpaspringdateusecases.repositories.RoleRepository;
import ma.ensa.jpaspringdateusecases.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

//@Component Pour indiquer a Spring que cette classe doit etre instancie au demmarrage
//Component c generale, on peut l'utiliser dans n'importe quel couche
//Couche Metier(Sevice) @Service,
@Service
@Transactional /*Les transactions vont etre delegue a Spring*/
@AllArgsConstructor /*il faut utiliser seulemnt ce constr avec parametre pour que Spring peut injecter
                    Injection des dependances via le constructeur*/
public class UserServiceImpl implements IUserService {
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = findUserByUserName(userName);
        Role role = findRoleByRoleName(roleName);
        if (user != null && user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);

        }


        //userRepository.save(user); pas necessaire prq la methode est transactionnelle : Spring commite a la fin du methode
        //prq il s'agit du Update


    }

    @Override
    public User authenticate(String username, String passwd) {
        User user = findUserByUserName(username);
        if (user == null) throw new RuntimeException("Bad crdentials");
        if (user.getPassword().equals(passwd)) {
            return user;
        }
        throw new RuntimeException("False credentials");
    }
}
