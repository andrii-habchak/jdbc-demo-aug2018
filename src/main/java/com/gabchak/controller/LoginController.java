package com.gabchak.controller;

import com.gabchak.model.User;
import com.gabchak.service.UserService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

import javax.servlet.http.Cookie;

public class LoginController implements Controller {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String email = request.getParamByName("email");
        String password = request.getParamByName("password");

        User user = userService.findByEmail(email);
        boolean isVerified = userService.validatePassword(user, password);
        ViewModel vm = null;

        if (user != null && isVerified) {
            vm = processAuthorised(user);
        } else {
            vm = processUnauthorised();
        }

        return vm;
    }

    private ViewModel processAuthorised(User user) {
        ViewModel vm = ViewModel.of("home");
        User registerUser = userService.addUser(user);
        Cookie cookie = new Cookie("MATE", registerUser.getToken());
        vm.setCookie(cookie);
        vm.addAttribute("user", user);
        return vm;
    }

    private ViewModel processUnauthorised() {
        ViewModel vm = ViewModel.of("login");
        vm.addAttribute("msg", true);
        return vm;
    }
}
