package back.decorwell.Entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import back.decorwell.Enum.Role;
import back.decorwell.Enum.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {


    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false, length = 80)
    private String fullname;

    @Column(nullable = false, length = 50)
    private String username;

    @Getter
    @Column(nullable = false, length = 150)
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 100)
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    Role role;

    @Getter
    @Enumerated(EnumType.STRING)
    State state;

    @Getter
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Getter
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}