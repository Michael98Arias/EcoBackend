package back.decorwell.Request;

import back.decorwell.Entity.Category;
import back.decorwell.Enum.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long categoryId;
    private Long sellerId;
    private String name;
    private String description;
    private Short amount;
    private BigDecimal price;
    private State state;
    private LocalDateTime createdAt;


}