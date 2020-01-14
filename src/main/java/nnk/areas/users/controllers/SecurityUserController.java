package nnk.areas.users.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import nnk.areas.users.models.binding.user.UserLoginModel;
import nnk.areas.users.models.binding.user.UserRegistrationModel;
import nnk.areas.users.services.SecurityUserDetailsService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class SecurityUserController {

    private final SecurityUserDetailsService userDetailsService;

    @Autowired
    public SecurityUserController(SecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");
        model.addAttribute("title", "Register");

        if(!model.containsAttribute("userRegistrationModel")){
            model.addAttribute("userRegistrationModel", new UserRegistrationModel());
        }

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(@Valid UserRegistrationModel userRegistrationModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationModel", userRegistrationModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationModel", bindingResult);
            return "redirect:/users/register";
        }

        this.userDetailsService.register(userRegistrationModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");
        model.addAttribute("title", "Login");
        model.addAttribute("user", new UserLoginModel());

        return "base-layout";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication auth) {

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }
}
