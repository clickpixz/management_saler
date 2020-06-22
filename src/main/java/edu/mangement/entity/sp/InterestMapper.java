package edu.mangement.entity.sp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestMapper {
    private BigDecimal total;
    private BigDecimal capital;
    private BigDecimal interest;
    private BigDecimal branchFee;
    private BigDecimal memberFee;

    public InterestMapper(BigDecimal total) {
        this.total = total;
    }

    public InterestMapper(BigDecimal total, BigDecimal capital, BigDecimal interest) {
        this.total = total;
        this.capital = capital;
        this.interest = interest;
    }
}
