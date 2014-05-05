package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.dto.UserDto;
import ru.javaschool.model.entities.User;
import ru.javaschool.services.UserService;

import javax.validation.Valid;
import java.text.ParseException;
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
        model.addAttribute("user", new UserDto());
        return "userView/registration";
    }

    /**
     * Adding new user to the database.
     *
     * @param userDto - target user to add.
     * @return - target view of registration.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, final RedirectAttributes redirectAttributes) {

        userDto.setRole("ROLE_USER");
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "Wrong data");
            return "redirect:/userView/registration";
        }
        User user = userDto.getUser();

        if (!userService.isCorrectAge(user)) {
            redirectAttributes.addFlashAttribute("msgf", "Age must be between 0 and 110 years. So, correct birth date!");
            return "redirect:/userView/registration";
        }
        String registerResult = userService.isRegistrationSuccess(user);
        if (!registerResult.equals("Success!")) {
            redirectAttributes.addFlashAttribute("msgf", registerResult);
            return "redirect:/userView/registration";
        }
        redirectAttributes.addFlashAttribute("msgg", "Registration success!");
        return "userView/login";
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

        User user = userService.getUserByPk(userId);
        if (user == null) {
            return "error404";
        }
        model.addAttribute("user", user);
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
            if (user == null) {
                return "error404";
            }
            return "userView/editor";
        }
        return "redirect:/error403";
    }

    /**
     * Save of new user
     *
     * @param userDto new User
     * @return view
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model, RedirectAttributes redAttr) {

        User user = userService.getUserByLogin(userDto.getLogin());
        if (user == null) {
            return "error404";
        }
        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msgf", "Wrong data");
            return "redirect:/userView/editor/" + user.getLogin();
        }
        user.setPassword(userDto.getPassword());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            user.setBirthDate(sdf.parse(userDto.getBirthDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (user.getLogin().equals(auth.getName())) {

            if (!userService.isCorrectAge(user)) {
                redAttr.addFlashAttribute("msgf", "Age must be between 0 and 110 years. So, correct birth date!");
                return "redirect:/userView/editor/" + user.getLogin();
            }
            String updateResult = userService.updateUser(user);
            if (!updateResult.equals("Success!")) {
                redAttr.addFlashAttribute("msgf", updateResult);
                return "redirect:/userView/editor/" + user.getLogin();
            }
            redAttr.addFlashAttribute("msgg", "Update successful!");
            model.addAttribute("user", user);
            return "userView/editor";
        }
        return "redirect:/error403";
    }
}
