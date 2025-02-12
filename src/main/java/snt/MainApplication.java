package snt;



import org.springframework.beans.factory.annotation.Value;
import snt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class MainApplication {

    @Autowired
    private final UserRepository userRepository;

    public MainApplication(UserRepository userRepository) {


        this.userRepository = userRepository;
    }
    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }

    @GetMapping("/")

    public String viewHomePage(Model model, @Value("${spring.application.name:'snt'}")String appName) {
        model.addAttribute("appName",appName);
        return "home";
    }
    @GetMapping("user")

    public String viewUserPage(Model model, @Value("${spring.application.name:'snt'}") String appName) {
        model.addAttribute("appName",appName);
        return "user";
    }
    @GetMapping("chart")

    public String viewChartPage(Model model, @Value("${spring.application.name:'snt'}") String appName) {
        model.addAttribute("appName",appName);
        return "chart";
    }



    @GetMapping("/login")
    public String viewLoginPage() {

        return "login";
    }
    @GetMapping("/error")
    public String viewErrorPage() {
        // model.addAttribute("allemplist", employeeServiceImpl.getAllEmployee());
        return "error";
    }
}
