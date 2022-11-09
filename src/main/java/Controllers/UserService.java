package Controllers;


import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class.getName());

    private static volatile UserService userService;
    private UserRepository userRepo;

    private UserService() {
        userRepo = UserRepository.getInstance();
    }

    public static UserService getInstance() {

        UserService result = userService;

        if (result == null) {
            synchronized (UserService.class) {
                result = userService;
                if (result == null) {
                    logger.info("creating a singleton of UserService");
                    userService = result = new UserService();
                }
            }
        }
        return result;
    }

    boolean updateEmail(User user, String updatedEmail) throws IOException {
        logger.info("in method: updateEmail");
        logger.debug("new email: " + updatedEmail);
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), updatedEmail, user.getName(), user.getPassword());
        updateData(newUser);
        return true;
    }

    boolean updateName(User user, String updatedName) throws IOException {
        logger.info("in method: updateName");
        logger.debug("new email: " + updatedName);
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), updatedName, user.getPassword());
        updateData(newUser);
        return true;
    }

    boolean updatePassword(User user, String updatedPassword) throws IOException {
        userRepo.deleteFile(user);
        User newUser = new User(user.getId(), user.getEmail(), user.getName(), updatedPassword);
        updateData(newUser);
        return true;
    }

    boolean deleteUser(User user) {
        logger.warn("About to delete user: " + user);
        userRepo.deleteFile(user);
        return true;
    }


    void updateData(User user) throws IOException {
        userRepo.writeToFile(user.getEmail() + ".json", user);
    }
}
