package edu.mangement.entity.sp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/7/2020
 * TIME : 4:48 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayQuantityMapper {
    private String day;
    private Long quantity;
}
