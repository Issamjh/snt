package snt;



import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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


    private final UserRepository userRepository;

    @Autowired
    public MainApplication(UserRepository userRepository) {


        this.userRepository = userRepository;
    }
    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }

    @RequestMapping({"/","/home"})

    public String viewHomePage(Model model, @Value("${spring.application.name:'snt'}")String appName) {
        model.addAttribute("appName",appName);
        return "home";
    }
    @RequestMapping("user")

    public String viewUserPage(Model model, @Value("${spring.application.name:'snt'}") String appName) {
        model.addAttribute("appName",appName);
        return "user";
    }
    @RequestMapping("chart")
    @PreAuthorize("ROLE_ADMIN")
    public String viewChartPage(Model model, @Value("${spring.application.name:'snt'}") String appName) {
        model.addAttribute("appName",appName);
        return "chart";
    }



    @RequestMapping("/login")
    public String viewLoginPage() {

        return "login";
    }
    @RequestMapping("/loginError")
    public String viewErrorPage(Model model) {
        model.addAttribute("loginError",true);
        return "login";
    }
}
