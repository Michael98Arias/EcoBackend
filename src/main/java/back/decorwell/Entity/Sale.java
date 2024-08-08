package back.decorwell.Entity;
import back.decorwell.Enum.State;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(nullable = false, length = 100)
    private Integer quantity;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @Getter
    @Enumerated(EnumType.STRING)
    State SaleState;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
