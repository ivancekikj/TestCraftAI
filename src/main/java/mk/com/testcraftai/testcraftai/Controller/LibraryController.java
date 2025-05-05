package mk.com.testcraftai.testcraftai.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibraryController {
    @GetMapping("/your-library")
    public String getYourLibraryPage() {
        return "library";
    }
}
