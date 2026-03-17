package in.purohith.Purohith.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Raw password you want to encode
        String rawPassword = "$2a$10$Y83t4cFstAclbLK3w6n2CugQlde95xbu0YOZzp9ceC8tGt4RTDgUy"; // <-- replace with your password
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
