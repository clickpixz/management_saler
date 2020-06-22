package edu.mangement.model.form.api;

import edu.mangement.entity.sp.InterestMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/2/2020
 * TIME : 9:21 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AjaxResponse {
    private String message;
    private List<?> list;
    private Long average;
    private InterestMapper interestMapper;
    public AjaxResponse(String message) {
        this.message = message;
    }
}
