package back.decorwell.Controller;

import back.decorwell.Request.RegisterRequest;
import back.decorwell.Entity.User;
import back.decorwell.Enum.Role;
import back.decorwell.Enum.State;
import back.decorwell.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController extends BaseController{
 @Autowired
    private UserService userService;

    @PostMapping(value = "listUsers")
    public List<User> getAll(){
        return  userService.getUsers();
    }
    @GetMapping("role/count")
    public Map<String, Integer> getRoleCount() {
        return userService.getRoleCount();
    }
    @GetMapping("user/{role}")
    public List<User> getUsersByRole(@PathVariable Role role) {
        return userService.getUsersByRole(role);
    }
    @PatchMapping("userState/{userId}/{newState}")
    public void updateUserState(@PathVariable Long userId, @PathVariable State newState) {
        userService.updateUserState(userId, newState);
    }
    @PostMapping("createUser")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
    @PutMapping("editUser/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
    }
}
