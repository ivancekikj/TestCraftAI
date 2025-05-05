package mk.com.testcraftai.testcraftai.Controller;


import lombok.AllArgsConstructor;
import mk.com.testcraftai.testcraftai.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration/save")
    public String registrationUser(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   Model model)
    {
        String result = userService.registration(username, email, password, confirmPassword);
        userService.registration(username,email,password,confirmPassword);
        if ("Save".equals(result)) {
            return "home";
        }
        else
        {
            model.addAttribute("error", result);
            return "registration";
        }
    }

    @GetMapping("/login")
    public  String  login()
    {
        return "login";
    }

    @PostMapping("/login/save")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model)
    {
        String result = userService.login(email, password);

        if ("OK".equals(result)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", result);
            return "login";
        }

    }



}
