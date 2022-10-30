package Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.*;

class AuthenticationService {

    Map<String, User> userTokens;

    public AuthenticationService() {
        this.userTokens = new HashMap<>();
    }

    String login(String email, String password) {
        try (FileReader reader = new FileReader(email + ".json")) {
            Gson gson = new Gson();
            User myUser = gson.fromJson(reader, User.class);
            if (Objects.equals(myUser.getPassword(), password)) {
                return createToken(myUser);
            } else {
                throw new InvalidParameterException("Password incorrect");
            }
        } catch (FileNotFoundException e) {
            throw new InvalidParameterException("User does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String createToken(User user) {
        String token = UUID.randomUUID().toString();
        userTokens.put(token, user);
        return token;
    }
    

    public static void updatePassword(String password) {
        
    }

    public static void updateName(String name) {

    }

    public static void updateEmail(String mail) {
        
    }


    public static void deleteUser(User user) {

    }
}

