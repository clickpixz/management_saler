package edu.mangement.entity.sp;

import edu.mangement.entity.Product;
import edu.mangement.model.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/6/2020
 * TIME : 4:46 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopProductSell {
    private ProductDTO productDTO;
    private Long quantity;

    public TopProductSell(Long productId, Long quantity) {
        this.productDTO = ProductDTO.builder().id(productId).build();
        this.quantity = quantity;
    }
}
