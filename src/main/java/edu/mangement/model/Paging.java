package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/23/2020
 * TIME : 10:40 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paging {
    private Integer totalRows;
    private Integer totalPages;
    private Integer indexPage;
    private Integer recordPerPage = 10;
    private Integer offset;
}
