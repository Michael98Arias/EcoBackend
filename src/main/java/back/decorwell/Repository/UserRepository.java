package back.decorwell.Repository;

import java.util.List;
import java.util.Optional;

import back.decorwell.Entity.User;
import back.decorwell.Enum.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);
}
