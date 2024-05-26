package ma.enset.jpamanytomany;

import ma.enset.jpamanytomany.entities.Role;
import ma.enset.jpamanytomany.entities.User;
import ma.enset.jpamanytomany.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaManyToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaManyToManyApplication.class, args);
	}
	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {
			User user1 = new User();
			user1.setUsername("zakariae");
			user1.setPassword("123456");

			userService.addNewUser(user1);


			User user2 = new User();
			user2.setUsername("issam");
			user2.setPassword("33445566");

			userService.addNewUser(user2);

			Stream.of("STUDENT", "USER", "ADMIN").forEach(role -> {
				Role role1 = new Role();
				role1.setRoleName(role);
				userService.addNewRole(role1);
			});



			userService.addRoleToUser("zakariae", "ADMIN");
			userService.addRoleToUser("zakariae", "USER");
			userService.addRoleToUser("issam", "USER");
			userService.addRoleToUser("issam", "STUDENT");


			try {
				User user = userService.authenticate("zakariae", "13456");
				System.out.println(user.getUserId());
				System.out.println(user.getUsername());
				System.out.println("Roles => ");
				user.getRoles().forEach(r->{
					System.out.println(r.toString());
				});
			}catch (Exception e){
				e.printStackTrace();
			}
		};
	}

}
