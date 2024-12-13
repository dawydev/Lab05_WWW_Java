package vn.edu.iuh.Lab05.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "candidates/login"; // Đảm bảo rằng tệp login.html nằm trong thư mục templates/candidates
    }
}