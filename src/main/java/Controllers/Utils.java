package Controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.regex.Pattern;

class Utils {
    private static Logger logger = LogManager.getLogger(Utils.class.getName());
    static void checkEmail(String temp) {
        logger.info(temp);
        if (temp == null) throw new InvalidParameterException("String empty");
        boolean matches = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(temp).matches();
        if (!matches) {
            logger.error("email not in format");
            throw new InvalidParameterException("Email not in correct format");
        }
    }

    static void checkPassword(String pass) {
        if (pass == null || pass.equals("")) throw new InvalidParameterException("Password not valid");
    }

    static void checkName(String name) {
        if (name.length() > 10) throw new InvalidParameterException();
    }

    static boolean isJsonFile(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf(".")).equals(".json");
    }
}
