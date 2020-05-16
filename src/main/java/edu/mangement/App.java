package edu.mangement;

import edu.mangement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Autowired
	private MemberRepository memberRepository;
	@Override
	public void run(String... args) throws Exception {
		var members = memberRepository.findAll();
		System.out.println(members);
	}
}
