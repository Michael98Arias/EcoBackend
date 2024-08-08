package back.decorwell.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    String token;
    Long id;
    String fullname;
    String username;
    String email;
    String role;
    String state;
    LocalDateTime createdAt;
    private String errorMessage;

}

