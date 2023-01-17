package ma.ensa.jpaspringdateusecases.service;

import ma.ensa.jpaspringdateusecases.entities.Role;
import ma.ensa.jpaspringdateusecases.entities.User;

public interface IUserService {
    User addUser(User user);
    Role addRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);/*on suppose que les 2 username et rolename sont uniques*/
    User authenticate(String username,String passwd);
}

