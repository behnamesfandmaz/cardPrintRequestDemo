package com.noway.cardPrintRequestDemo.framework.controller;

import com.noway.cardPrintRequestDemo.framework.dto.user.UserDTO;
import com.noway.cardPrintRequestDemo.framework.service.inter.user.IUserService;
import com.noway.cardPrintRequestDemo.framework.validation.userValidation.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    private final IUserService userService;

    private final UserValidator userValidator;

    @Autowired
    public UserController(IUserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(ModelMap model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {

        LOG.info("register user with Username = "+userForm.getUsername());

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null) {
            LOG.info("logOut User");
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome() {
        return "welcome";
    }


}
