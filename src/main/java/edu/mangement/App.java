package edu.mangement;

import edu.mangement.entity.sp.TopProductSell;
import edu.mangement.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private OrderDetailService orderDetailService;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		fullTextEntityManager.createIndexer().startAndWait();
//		System.out.println(productInStockRepository.searchProductInStock(null,"CANIAFA Hà Nội","M",1, Pageable.unpaged()).getContent());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = simpleDateFormat.parse("2020-05-25 00:00:00");
        Date date2 = simpleDateFormat.parse("2020-05-18 00:00:00");
        orderDetailService.getTopProductSell(date1, null)
                .forEach(System.out::println);

    }

}
