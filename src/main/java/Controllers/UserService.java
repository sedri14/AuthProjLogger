package Controllers;


import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

class UserService {

    static boolean createUser (User user){
        return true;
    }
    static boolean updateEmail(User user, String updatedEmail) throws IOException {
        deleteFile(user.getEmail()+".json");
        User newUser = new User(user.getId(),updatedEmail, user.getName(), user.getPassword());
        updateData(newUser);
        return true;
    }
    static boolean updateName(User user, String updatedName) throws IOException {
        deleteFile(user.getEmail()+".json");
        User newUser = new User(user.getId(), user.getEmail(), updatedName, user.getPassword());
        updateData(newUser);
        return true;
    }
    static boolean updatePassword(User user, String updatedPassword) throws IOException {
        deleteFile(user.getEmail()+".json");
        User newUser = new User(user.getId(),user.getEmail(), user.getName(), updatedPassword);
        updateData(newUser);
        return true;
    }
    static boolean deleteUser(User user){
        deleteFile(user.getEmail()+".json");
        return true;
    }


    static void deleteFile(String path)  {
            File file = new File(path);
            file.delete();
    }

    static void updateData(User user) throws IOException {
        Gson gson = new Gson();
        try (FileWriter fw = new FileWriter(user.getEmail()+".json")){
            String defaultConfig = gson.toJson(user);
            fw.write(defaultConfig);
        } catch (IOException e){
            throw new IOException("cant write to new file to update");
        }
    }
}
