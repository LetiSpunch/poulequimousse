package fr.laPouleQuiMousse.controllers;

import fr.laPouleQuiMousse.models.User;
import fr.laPouleQuiMousse.models.UserNewPassword;
import fr.laPouleQuiMousse.services.EmailService;
import fr.laPouleQuiMousse.services.MessageService;
import fr.laPouleQuiMousse.services.RequestService;
import fr.laPouleQuiMousse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService mailService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RequestService requestService;

    @Value("${password.token.durability}")
    private Integer tokenDurability;

    @GetMapping("/login")
    public String login() {
        return "home/login/login";
    }

    @GetMapping("/login/forgot")
    public String forgot() {
        return "home/login/forgot";
    }

    @PostMapping("/login/forgot")
    public String forgot(String email, Model model, final RedirectAttributes redirectAttributes) {
        User user = userService.findByEmail(email);

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", messageService.get("forgotPassword.emailNotFound"));
            return "redirect:/login/forgot";
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, tokenDurability);

        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpirationDate(cal.getTime());

        try {
            final String[] params = new String[] {
                    requestService.getBaseUrl("/login/reset/" + user.getToken()),
                    tokenDurability.toString()
            };
            mailService.sendSimpleMessage(user.getEmail(), messageService.get("users.mail.forgotPassword.byUser.subject"), messageService.get("users.mail.forgotPassword.byUser.message", params));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        userService.saveToken(user);

        model.addAttribute("success", messageService.get("forgotPassword.emailSent"));
        return "home/login/forgot";
    }

    @GetMapping("/login/reset/{token}")
    public String reset(@PathVariable @ModelAttribute String token, Model model, final RedirectAttributes redirectAttributes) {
        User user = userService.findByToken(token);

        if (user == null) {
            return "redirect:/login/forgot";
        }

        if (user.getTokenExpirationDate().before(new Date())) {
            redirectAttributes.addFlashAttribute("message", messageService.get("forgotPassword.expiredToken"));
            return "redirect:/login/forgot";
        }

        model.addAttribute(new UserNewPassword(token));

        return "home/login/reset";
    }

    @PostMapping("/login/reset")
    public String reset(@Valid UserNewPassword userNewPassword, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            result.reject("generic.save.error");
            return "home/login/reset";
        }

        if(!userNewPassword.getPassword().equals(userNewPassword.getPasswordConfirmation())) {
            result.reject("generic.save.error");
            result.rejectValue("passwordConfirmation", "users.save.error.passwordsDoesNotMatch");
            return "home/login/reset";
        }

        User user = userService.findByToken(userNewPassword.getToken());
        if (user == null) {
            return "redirect:/login/forgot";
        }

        user.setPassword(userNewPassword.getPassword());
        userService.savePassword(user);

        redirectAttributes.addFlashAttribute("success", messageService.get("forgotPassword.passwordChanged"));
        return "redirect:/login";
    }
}

