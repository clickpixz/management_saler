package edu.mangement.entity.sp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/5/2020
 * TIME : 12:09 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResult {
    private String time;
    private Long quantity;
}
