package back.decorwell.Request;

import back.decorwell.Enum.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    private State state;
    private LocalDateTime createdAt;
}
