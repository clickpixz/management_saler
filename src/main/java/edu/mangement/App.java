package edu.mangement;

import edu.mangement.entity.Branch;
import edu.mangement.entity.Menu;
import edu.mangement.entity.Product;
import edu.mangement.repository.ProductRepository;
import edu.mangement.service.CategoryService;
import edu.mangement.service.ProductService;
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
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		fullTextEntityManager.createIndexer().startAndWait();
	}

}
