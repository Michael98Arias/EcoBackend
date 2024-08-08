package back.decorwell.Entity;

import back.decorwell.Enum.State;
import back.decorwell.Interface.CategoryDetails;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements CategoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Short amount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Getter
    @Enumerated(EnumType.STRING)
    State state;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
