package back.decorwell.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String fullname;
    String username;
    String email;
    String password;
    String role;
    LocalDateTime createdAt;
}
