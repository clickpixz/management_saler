package edu.mangement.mapper;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:19 PM
 */
@Component
@FunctionalInterface
public interface EntityMapper<V,T> {
    V toEntity(T t);
}
