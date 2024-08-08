package back.decorwell.Entity;

import back.decorwell.Enum.State;
import back.decorwell.Interface.CategoryDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category implements CategoryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Getter
    @Enumerated(EnumType.STRING)
    State state;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
