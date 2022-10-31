package Controllers;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class UserController {

    AuthenticationService authService;
    UserService userService;

    public UserController() {
        this.authService = AuthenticationService.getInstance();
        this.userService = UserService.getInstance();
    }

    public boolean updateEmail(String mail, String token) throws IOException {
        try{
            Utils.checkEmail(mail);
        }catch (InvalidParameterException ip){
            throw new InvalidParameterException("Email not in correct format");
        }
        User user = authService.validate(token);
        return userService.updateEmail(user, mail);
    }

    public boolean updateName(String name, String token) throws IOException {
        try{
            Utils.checkName(name);
        }catch (InvalidParameterException ip){
            throw new InvalidParameterException("Name not in correct format");
        }
        User user = authService.validate(token);
        return userService.updateName(user,name);
    }
    public boolean updatePassword(String password, String token) throws IOException {
        try{
            Utils.checkPassword(password);
        }catch (InvalidParameterException ip){
            throw new InvalidParameterException("Email not in correct format");
        }
        User user = authService.validate(token);
        return userService.updatePassword(user,password);
    }

    public boolean deleteUser(String token){
        User user = authService.validate(token);
        return userService.deleteUser(user);
    }

}

