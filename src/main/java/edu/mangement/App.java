package edu.mangement;

import edu.mangement.entity.Member;
import edu.mangement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class App implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Autowired
	private MemberRepository memberRepository;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Member memberRepositoryOne = memberRepository.getOne((long) 1);
		System.out.println(memberRepositoryOne);
		var member = Member.builder()
							.username("tranvantam")
							.password("123456")
							.name("Tran Van Tam")
							.sex(0)
							.doB(new Date().toString())
							.branch(memberRepositoryOne.getBranch())
							.role(memberRepositoryOne.getRole())
							.activeFlag(1)
							.build();
		System.out.println(member);
//		Member memberResult = memberRepository.save(member);
//		System.out.println(memberResult);
	}
}
