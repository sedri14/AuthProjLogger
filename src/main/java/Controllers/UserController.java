package Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public class UserController {

    private static Logger logger = LogManager.getLogger(UserController.class.getName());
    AuthenticationService authService;
    UserService userService;

    public UserController() {
        this.authService = AuthenticationService.getInstance();
        this.userService = UserService.getInstance();
    }

    public boolean updateEmail(String mail, String token) throws IOException {
        logger.info("Entered method: updateEmail");
        logger.debug(mail);
        try{
            Utils.checkEmail(mail);
        }catch (InvalidParameterException ip){
            logger.error("Email not in format");
            throw new InvalidParameterException("Email not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updateEmail(user, mail);
        if (status) {
            authService.reloadUser(mail, token);
        }
        return status;
    }

    public boolean updateName(String name, String token) throws IOException {
        logger.info("Entered method: updateName");
        logger.debug(name);
        try{
            Utils.checkName(name);
        }catch (InvalidParameterException ip){
            logger.error("Name not in format");
            throw new InvalidParameterException("Name not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updateName(user, name);
        if (status) {
            authService.reloadUser(user.getEmail(), token);
        }
        return status;
    }
    public boolean updatePassword(String password, String token) throws IOException {
        logger.info("Entered method: updatePassword");
        logger.debug(password);
        try{
            Utils.checkPassword(password);
        }catch (InvalidParameterException ip){
            logger.error("Password not in format");
            throw new InvalidParameterException("Password not in correct format");
        }
        User user = authService.validate(token);
        boolean status = userService.updatePassword(user, password);
        if (status) {
            authService.reloadUser(user.getEmail(), token);
        }
        return status;
    }

    public boolean deleteUser(String token){
        logger.info("Entered method: deleteUser");
        User user = authService.validate(token);
        boolean status = userService.deleteUser(user);
        if (status) {
            authService.removeToken(token);
        }
        return status;
    }

}

