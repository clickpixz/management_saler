package edu.mangement.model;

import lombok.*;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/23/2020
 * TIME : 10:40 PM
 */
@Builder
@EqualsAndHashCode
public class Paging {
    private Integer totalRows;
    private Integer totalPages;
    private Integer indexPage;
    private Integer recordPerPage = 10;
    private Integer offset;

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(Integer indexPage) {
        this.indexPage = indexPage;
    }

    public Integer getRecordPerPage() {
        return recordPerPage;
    }

    public void setRecordPerPage(Integer recordPerPage) {
        this.recordPerPage = recordPerPage;
    }

    public Integer getOffset() {
        if (indexPage > 0) {
            offset = indexPage * recordPerPage - recordPerPage;
        }
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
