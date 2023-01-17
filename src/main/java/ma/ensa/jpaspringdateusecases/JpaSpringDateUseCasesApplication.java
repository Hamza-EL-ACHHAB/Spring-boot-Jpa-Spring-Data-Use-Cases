package ma.ensa.jpaspringdateusecases;

import ma.ensa.jpaspringdateusecases.entities.Role;
import ma.ensa.jpaspringdateusecases.entities.User;
import ma.ensa.jpaspringdateusecases.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaSpringDateUseCasesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSpringDateUseCasesApplication.class, args);
    }
    @Bean/*pour que la methode s'execute au demmarrage de l'application*/
    CommandLineRunner start(IUserService userService){
        return args -> {
            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addUser(u);

            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("123456");
            userService.addUser(u2);

            Stream.of("STUDENT","USER","ADMIN").forEach(rolename -> {
                Role role1 = new Role();
                role1.setRoleName(rolename);
                userService.addRole(role1);
            });

            userService.addRoleToUser("user1","STUDENT");
            userService.addRoleToUser("user1","USER");
            userService.addRoleToUser("admin","USER");
            userService.addRoleToUser("admin","ADMIN");

            try {
                User user = userService.authenticate("user1","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Roles :");
                user.getRoles().forEach(role -> {
                    System.out.println(role);
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }



        };

        //c equivalent au code suivant
        /*return  new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

            }
        };
*/
    }

}
