import Controllers.AuthenticationController;
import Controllers.UserController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        AuthenticationController authController = new AuthenticationController();
        UserController userController = new UserController();

        authController.register("gideon@gmail.com", "Gideon", "Figglophobia");
        authController.register("dvir.shaul@gmail.com", "Dvir", "dvir1234");
        String dvirToken = authController.login("dvir.shaul@gmail.com", "dvir1234");
        String gideonToken = authController.login("gideon@gmail.com", "Figglophobia");
        try {
            userController.updateEmail("gideon2@gmail.com", gideonToken);
            String gideonToken2 = authController.login("gideon@gmail.com", "Figglophobia");
            userController.updateName("Gideoniii", gideonToken);
            userController.updateName("Gideoniii", gideonToken2);
            userController.updatePassword("Dvir2213",dvirToken);
            userController.deleteUser(gideonToken2);
        } catch (IOException e) {
            System.out.println("cant update user details");
        }
    }
}