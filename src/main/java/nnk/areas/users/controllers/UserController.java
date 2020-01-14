package nnk.areas.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("users")
public class UserController {

    @Autowired
    public UserController() {
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("view", "user/profile");
        model.addAttribute("title", "Profile");

        return "base-layout";
    }

}
