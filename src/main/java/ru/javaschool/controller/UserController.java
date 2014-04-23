package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String logIn(Model model) {
        model.addAttribute("user", new User());
        return "userView/login";
    }

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
        user.setRole("ROLE_CLIENT");
        userService.isRegistrationSuccess(user);
        return "userView/registrationResult";
    }

    @RequestMapping(value = "/tickets/{userId}")
    public String getUsersTickets(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.getUserByPk(userId));
        model.addAttribute("ticketList", userService.getUsersTicketList(userId));
        return "userView/tickets";
    }




}
