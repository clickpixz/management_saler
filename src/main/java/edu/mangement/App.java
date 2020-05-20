package edu.mangement;

import edu.mangement.entity.Menu;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.RoleDTO;
import edu.mangement.repository.MemberRepository;
import edu.mangement.repository.MenuRepository;
import edu.mangement.repository.OrderRepository;
import edu.mangement.repository.RoleRepository;
import edu.mangement.service.MenuService;
import edu.mangement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class App implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
	}
}
