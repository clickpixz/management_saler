package edu.mangement.dto.mapper;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:35 AM
 */
@Component
@FunctionalInterface
public interface ModelMapper<T,V> {
    T toDTO(V v);
}
