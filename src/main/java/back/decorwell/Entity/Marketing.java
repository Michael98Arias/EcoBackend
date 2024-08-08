package back.decorwell.Entity;

import back.decorwell.Enum.State;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "marketing")
public class Marketing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, length = 100)
    private String campaignName;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Getter
    @Enumerated(EnumType.STRING)
    State state;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
