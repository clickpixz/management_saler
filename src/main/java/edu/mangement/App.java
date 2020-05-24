package edu.mangement;

import edu.mangement.entity.Branch;
import edu.mangement.entity.Menu;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class App implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	@Autowired
	private EntityManager entityManager;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
////		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Branch.class).get();
////		Query query = queryBuilder.keyword().onFields("name", "address","phone").matching("Hà").createQuery();
////		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Branch.class);
////		List<Branch> resultList = fullTextQuery.getResultList();
////		resultList.forEach(System.out::println);
////		System.out.println(resultList.size());
//		fullTextEntityManager.createIndexer().startAndWait();
	}

}
