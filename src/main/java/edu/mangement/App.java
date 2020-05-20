package edu.mangement;

import edu.mangement.entity.Branch;
import edu.mangement.entity.Menu;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.RoleDTO;
import edu.mangement.repository.*;
import edu.mangement.service.MenuService;
import edu.mangement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	@Autowired
	private BranchRepository branchRepository;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		System.out.println(branchRepository.findAllByActiveFlag(1,PageRequest.of(0,1)));
	}
}
