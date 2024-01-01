package com.springsecurityjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springsecurityjwt.entity.Role;
import com.springsecurityjwt.entity.User;
import com.springsecurityjwt.repositories.UserRepository;

@SpringBootApplication
public class SpringSecurityJwt1Application implements CommandLineRunner{

	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwt1Application.class, args);
	}
	
	public void run(String... args) {
		User adminAccount = userRepo.findByRole(Role.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			user.setFirstname("admin");
			user.setSecondname("Admin");
			user.setEmail("admin@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.ADMIN);
			userRepo.save(user);
		}
	}

}
