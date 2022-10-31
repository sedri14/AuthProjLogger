import Controllers.AuthenticationController;
import Controllers.UserController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AuthenticationController authController = new AuthenticationController();
        UserController userController = new UserController();

        authController.register("gideon.jaffe@gmail.com", "Gideon", "Figglophobia");
        String gideonToken = authController.login("gideon.jaffe@gmail.com", "Figglophobia");
        try {
            userController.updateEmail("gideon_jaffe@gmail.com", gideonToken);
            userController.updateName("Gideon_J", gideonToken);
            userController.updatePassword("Awesome", gideonToken);
        } catch (IOException e) {
            System.out.println("cant update user details");
        }
    }
}