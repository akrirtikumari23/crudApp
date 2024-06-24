package hexa.wynkong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Handle the error gracefully, e.g., return a custom error page
        return "resultError"; // Assuming you have an error.html or error.jsp in your templates
    }

    public String getErrorPath() {
        return "/error";
    }
}

