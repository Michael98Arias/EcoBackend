package back.decorwell.Service;


import back.decorwell.Request.RegisterRequest;
import back.decorwell.Entity.User;
import back.decorwell.Enum.State;
import back.decorwell.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import back.decorwell.Enum.Role;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Map<String, Integer> getRoleCount() {
        List<User> allUsers = userRepository.findAll();
        Map<String, Integer> roleCountMap = new HashMap<>();

        for (User user : allUsers) {
            String role = user.getRole().toString();
            roleCountMap.put(role, roleCountMap.getOrDefault(role, 0) + 1);
        }

        return roleCountMap;
    }
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
    public void updateUserState(Long userId, State newState) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(newState);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }
    public User register(RegisterRequest request) {
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
        return user;
    }
    public void updateUser(Long userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (updatedUser.getFullname() != null) {
                user.setFullname(updatedUser.getFullname());
            }
            if (updatedUser.getUsername() != null) {
                user.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null) {
                user.setPassword(updatedUser.getPassword());
            }
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }
//    public Optional<User> getUser(Long id){
//        return userRepository.findById(id);
//    }
//
//    public void saveOrUpdate(User user){
//        userRepository.save(user);
//    }

}
