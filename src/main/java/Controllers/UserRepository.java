package Controllers;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class UserRepository {

    private static Logger logger = LogManager.getLogger(UserRepository.class.getName());
    private static volatile UserRepository userRepo;
    Map<String, User> usersCache;

    static UserRepository getInstance() {
        UserRepository result = userRepo;

        if (result == null) {
            synchronized (UserRepository.class) {
                result = userRepo;
                if (result == null) {
                    userRepo = result = new UserRepository();
                    logger.info("creating singleton user repository");
                }
            }
        }
        return result;
    }

    private UserRepository() {
        usersCache = new HashMap<>();
        logger.info("in c'tor");
        loadAllUsersToCache(new File("src\\main\\java\\Controllers\\users"));
    }

    User readFromCache(String email){
        logger.warn("about to read users from cache");
        return usersCache.get(email);
    }

    private void loadAllUsersToCache(File folder) {
        for (File fileEntry: folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                if (Utils.isJsonFile(fileEntry)) {
                    User user = readFromFile(fileEntry.getAbsolutePath());
                    logger.debug(user);
                    usersCache.put(user.getEmail(),user);
                }
            }
        }
    }

    void deleteFile(String path) {
        logger.info(String.format("in delete file, deleteing path: %s", path));
        File file = new File("src\\main\\java\\Controllers\\users\\" + path);
        boolean b= file.delete();
    }

    void deleteFile(User user) {
        logger.info("delete file");
        if (!usersCache.containsKey(user.getEmail())) {
            logger.error("trying to remove user that doesnt exist");
            throw new IllegalArgumentException("cant remove user that doesnt exist");
        }
        usersCache.remove(user.getEmail());
        deleteFile(user.getEmail() + ".json");
    }

    void writeToFile(String fileName, User user) throws IOException {
        logger.info("in method: writeToFile");
        logger.debug(fileName,user);
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter("src\\main\\java\\Controllers\\users\\" + fileName)) {
            String userJson = gson.toJson(user);
            fw.write(userJson);
            usersCache.put(user.getEmail(), user);
        } catch (IOException e) {
            logger.error(String.format("can write to file %s", fileName));
            throw new IOException("cant write to new file to update");
        }
    }

    private User readFromFile(String fileName) {
        logger.info("in method: readFromFile");
        logger.debug(fileName);
        User readUser = null;
        try (FileReader fr = new FileReader(fileName)) {
            Gson gson = new Gson();
            readUser = gson.fromJson(fr, User.class);
            usersCache.put(readUser.getEmail(), readUser);
        } catch(FileNotFoundException e) {
            logger.error("file not found");
            throw new RuntimeException("file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return readUser;
    }
}
