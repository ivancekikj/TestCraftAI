package mk.com.testcraftai.testcraftai.Service;

import mk.com.testcraftai.testcraftai.Entity.Users;
import mk.com.testcraftai.testcraftai.Enum.UserRole;
import mk.com.testcraftai.testcraftai.Repository.UserReporsitory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    private final UserReporsitory userReporsitory;
    private final View error;

    public UserServiceImpl(UserReporsitory userReporsitory, View error) {
        this.userReporsitory = userReporsitory;
        this.error = error;
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    @Override
    public String registration(String username, String email, String password, String confirmPassword)
    {

        if(userReporsitory.findByEmail(email).isPresent())
        {
            return "User with this email already exists.";
        }
        if (!password.equals(confirmPassword)) {
             return "Passwords do not match.";
        }
        if (!isValidPassword(password)) {
            return "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one special character.";
        }

        Users users = new Users();
        users.setUsername(username);
        users.setEmail(email);
        users.setPassword(password);
        users.setRole(UserRole.STUDENT);
        userReporsitory.save(users);
        return "Save";
    }

    @Override
    public String  login(String email, String password) {

        Optional<Users> userOpt = userReporsitory.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "User not found.";
        }

        Users user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            return "Invalid password.";
        }

        return "OK";
    }
}
