package edu.mangement.entity.sp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/4/2020
 * TIME : 5:53 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuResult {
    private String dayOfTheMonth;
    private Long quantity;
}
