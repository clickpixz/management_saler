package edu.mangement.service;

import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/26/2020
 * TIME : 5:37 PM
 */
@Service
public class FullTextSearchEngine<T> {
    @Autowired
    private EntityManager entityManager;

    public FullTextQuery getFullTextQuery(SearchForm searchForm, Paging paging, Class<T> zClass, String... parameter) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(zClass).get();
        Query query = queryBuilder.keyword().onFields(parameter)
                .matching(searchForm.getField()).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, zClass);
        if (paging != null) {
            //so ban ghi 1 page
            fullTextQuery.setMaxResults(paging.getRecordPerPage());
            //ban ghi dau tien
            fullTextQuery.setFirstResult(paging.getOffset());
            //tong so ban ghi
            paging.setTotalRows((long) fullTextQuery.getResultSize());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
        return fullTextQuery;
    }
}
