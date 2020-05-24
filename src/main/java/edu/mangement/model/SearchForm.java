package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/24/2020
 * TIME : 10:51 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchForm {
    private String field;
}
