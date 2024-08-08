package back.decorwell.Auth;


import back.decorwell.Enum.State;
import back.decorwell.Request.LoginRequest;
import back.decorwell.Request.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import back.decorwell.Jwt.JwtService;
import back.decorwell.Enum.Role;
import back.decorwell.Entity.User;
import back.decorwell.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        UserDetails userDetails;
        try {
            userDetails = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        } catch (UsernameNotFoundException ex) {
            return AuthResponse.builder()
                    .errorMessage("not_found")
                    .build();
        }

        User user = (User) userDetails;

        if (user.getState() != State.ACTIVE) {
            return AuthResponse.builder()
                    .errorMessage("disabled")
                    .build();
        }

        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .fullname(user.getFullname())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .state(String.valueOf(user.getState()))
                .createdAt(user.getCreatedAt())
                .build();
    }


    public AuthResponse register(RegisterRequest request) {
        LocalDateTime createdAt = request.getCreatedAt() != null ? request.getCreatedAt() : LocalDateTime.now();
        User user = User.builder()
                .fullname(request.getFullname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.valueOf(request.getRole()))
                .state(State.ACTIVE)
                .createdAt(createdAt)
                .build();

        userRepository.save(user);


        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(user.getId())
                .fullname(user.getFullname())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .state(String.valueOf(user.getState()))
                .createdAt(user.getCreatedAt())
                .build();

    }

}