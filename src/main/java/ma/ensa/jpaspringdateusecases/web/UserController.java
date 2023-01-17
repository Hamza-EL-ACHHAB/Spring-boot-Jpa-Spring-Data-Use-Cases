package ma.ensa.jpaspringdateusecases.web;

import ma.ensa.jpaspringdateusecases.entities.User;
import ma.ensa.jpaspringdateusecases.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/users/{username}")
    public User user(@PathVariable(name ="username") String userName){
        User user = userService.findUserByUserName(userName);
        return user;
    }
}
