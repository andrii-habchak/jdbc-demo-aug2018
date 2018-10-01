package com.gabchak.controller;

import com.gabchak.service.UserService;
import com.gabchak.web.Requeast;
import com.gabchak.web.ViewModel;

public class RegisterController implements Controller {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel process(Requeast requeast) {
        return null;
    }
}
