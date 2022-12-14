import Controllers.AuthenticationController;
import Controllers.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.security.InvalidParameterException;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {


        AuthenticationController authController = new AuthenticationController();
        UserController userController = new UserController();

        authController.register("gideon@gmail.com", "Gideon", "Figglophobia");
        authController.register("dvir.shaul@gmail.com", "Dvir", "dvir1234");
        String dvirToken = authController.login("dvir.shaul@gmail.com", "dvir1234");
        String gideonToken = authController.login("gideon@gmail.com", "Figglophobia");
        try {

            System.out.println("-------trying to change email:");
            if (userController.updateEmail("gideon2@gmail.com", gideonToken)) {
                System.out.println("change email successful!");
            }

            try {
                System.out.println("-------trying to login with previous email:");
                authController.login("gideon@gmail.com", "Figglophobia");
            } catch (IllegalArgumentException e) {
                logger.error("can't log in with previous email");
                System.out.println(e + " - test successful!");
            }

            System.out.println("-------trying to change name:");
            if (userController.updateName("Gideoniii", gideonToken)) {
                System.out.println("change name successful!");
            }
            System.out.println("-------trying to change password:");
            if (userController.updatePassword("Dvir2213", dvirToken)) {
                System.out.println("change password successful!");
            }

            System.out.println("-------trying to change password with invalid token:");
            try {
                userController.updatePassword("Dvir2213", "ooblah");
                System.out.println("change password successful!");
            } catch (InvalidParameterException e) {
                logger.error("can't update password with invalid token");
                System.out.println(e + " - test successful!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}