package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.model.entities.User;
import ru.javaschool.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/userView")
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * Log in app
     *
     * @param model - model view
     * @return - target url
     */
    @RequestMapping(value = "/login")
    public String logIn(Model model) {
        model.addAttribute("user", new User());
        return "userView/login";
    }

    /**
     * Registration of user.
     *
     * @param model - model view
     * @return - target url
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
        return "userView/registration";
    }

    /**
     * Adding new user to the database.
     *
     * @param user - target user to add.
     * @return - target view of registration.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        user.setRole("ROLE_USER");
        userService.isRegistrationSuccess(user);
        return "userView/registrationResult";
    }

    /**
     * Get target users tickets.
     *
     * @param userId - user identifier
     * @param model  - model view of tickets
     * @return - target url
     */
    @RequestMapping(value = "/tickets/{userId}")
    public String getUsersTickets(@PathVariable("userId") Long userId, Model model) {

        model.addAttribute("user", userService.getUserByPk(userId));
        model.addAttribute("ticketList", userService.getUsersTicketList(userId));
        return "userView/tickets";
    }

    /**
     * View, where user can edit his own profile, and see his tickets
     *
     * @param login - user identifier
     * @param model - model view
     * @return - target url
     */
    @RequestMapping(value = "/editor/{login}")
    public String viewUser(@PathVariable("login") String login, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals(login)) {
            User user = userService.getUserByLogin(login);
            model.addAttribute("user", user);
            model.addAttribute("ticketList", userService.getUsersTicketList(user.getUserId()));
            return "userView/editor";
        }
        return "redirect:/error403";
    }

    /**
     * Save of new user
     *
     * @param user new User
     * @return view
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (user.getLogin().equals(auth.getName())) {
            if (user.getRole().equals("ROLE_ADMIN")) {
                user.setRole("ROLE_ADMIN");
            } else {
                user.setRole("ROLE_USER");
            }
            userService.updateUser(user);
            model.addAttribute("user", user);
            return "userView/editor";
        }
        return "redirect:/error403";
    }
}
