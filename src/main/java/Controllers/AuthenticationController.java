package Controllers;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class AuthenticationController {

    public String login(String email, String password) {
        checkEmail(email);
        checkPassword(password);
        AuthenticationService service = new AuthenticationService();
        return service.login(email, password);
    }

    private void checkEmail(String temp) {
        if (temp == null) throw new InvalidParameterException("String empty");
        boolean matches = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$").matcher(temp).matches();
        if (!matches) throw new InvalidParameterException("Email not in correct format");
    }

    private void checkPassword(String pass) {
        if (pass == null || pass.equals("")) throw new InvalidParameterException("Password not valid");
    }

    public void register(String email, String name, String password) {
        throw new UnsupportedOperationException();
    }
}

