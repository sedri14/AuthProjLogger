package Controllers;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserController {

    AuthenticationService authService;
    UserService userService;

    public UserController() {
        this.authService = AuthenticationService.getInstance();
        this.userService = UserService.getInstance();
    }

    public boolean updateEmail(String mail, String token) throws IOException {
        User user = authService.validate(token);
        return userService.updateEmail(user, mail);
    }

    public boolean updateName(String name, String token) throws IOException {
        User user = authService.validate(token);
        return userService.updateName(user,name);
    }
    public boolean updatePassword(String password, String token) throws IOException {
        User user = authService.validate(token);
        return userService.updatePassword(user,password);
    }

    public boolean deleteUser(String token){
        User user = authService.validate(token);
        return userService.deleteUser(user);
    }

}

