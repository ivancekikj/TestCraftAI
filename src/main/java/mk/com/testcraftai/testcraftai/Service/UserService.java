package mk.com.testcraftai.testcraftai.Service;


public interface UserService {

    String  registration(String username,String email, String password, String confirmPassword);
    String login(String email, String password);
}
