package com.gabchak.controller;

import com.gabchak.model.User;
import com.gabchak.service.UserService;
import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class RegisterController implements Controller {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Request request) {
        String email = request.getParamByName("email");
        String password = request.getParamByName("password");
        User user = new User(email, password);
        ViewModel vm = ViewModel.of("welcome");
        vm.addAttribute("user", userService.addUser(user));
        return vm;
    }
}
