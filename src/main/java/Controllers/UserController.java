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

    public void updateEmail(String mail, String token) throws IOException {
//        if (mail == null)
//            return;
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//        Pattern pat = Pattern.compile(emailRegex);
//        if(pat.matcher(mail).matches()) {
        User user = authService.validate(token);
        userService.updateEmail(user, mail);
    }

    public void updateName(String name, String token) throws IOException {
        User user = authService.validate(token);
        userService.updateName(user,name);
    }
    public void updatePassword(String password, String token) throws IOException {
        User user = authService.validate(token);
        userService.updatePassword(user,password);
    }


    public void deleteUser(String token){
        User user = authService.validate(token);
        userService.deleteUser(user);

    }

}

