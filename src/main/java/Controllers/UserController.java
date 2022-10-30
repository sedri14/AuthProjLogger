package Controllers;

import java.io.IOException;
import java.util.regex.Pattern;

public class UserController {


    public static void updateEmail(String mail, String token) throws IOException {
//        if (mail == null)
//            return;
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
//                "[a-zA-Z0-9_+&*-]+)*@" +
//                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
//                "A-Z]{2,7}$";
//        Pattern pat = Pattern.compile(emailRegex);
//        if(pat.matcher(mail).matches()) {
        User user = AuthenticationService.validate(token);
        UserService.updateEmail(user, mail);
    }

    public static void updateName(String name, String token) throws IOException {
        User user = AuthenticationService.validate(token);
        UserService.updateName(user,name);
    }
    public static void updatePassword(String password, String token) throws IOException {
        User user = AuthenticationService.validate(token);
        UserService.updatePassword(user,password);
    }


    public static void deleteUser(String token){
        User user = AuthenticationService.validate(token);
        UserService.deleteUser(user);

    }

}

