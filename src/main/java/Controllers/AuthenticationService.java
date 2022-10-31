package Controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.security.InvalidParameterException;
import java.util.*;

class AuthenticationService {

    private static volatile AuthenticationService authService;
    static int id = 0;
    Map<String, User> userTokens;

    private AuthenticationService() {
        this.userTokens = new HashMap<>();
    }

    public static AuthenticationService getInstance() {

        AuthenticationService result = authService;

        if (result == null) {
            synchronized (AuthenticationService.class) {
                result = authService;
                if (result == null) {
                    authService = result = new AuthenticationService();
                }
            }
        }
        return result;
    }

    void register(String email, String name, String password) {

        if (!checkIfUserExists(email)) {
            User user = new User(id++, email, name, password);
            try {
                BufferedWriter output = new BufferedWriter(new FileWriter(email + ".json"));
                output.write(new Gson().toJson(user));
            } catch (IOException e) {
                System.out.println("Couldn't write to file");
                throw new RuntimeException(e);
            }
        }
    }

    User validate(String token) {
        if (!userTokens.containsKey(token)) {
            throw new InvalidParameterException("Token incorrect");
        }
        return userTokens.get(token);
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


    private static boolean checkIfUserExists(String email) {
        try (FileReader fr = new FileReader(email + ".json")) {
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}

