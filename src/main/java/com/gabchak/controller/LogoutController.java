package com.gabchak.controller;

import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class LogoutController implements Controller {

    @Override
    public ViewModel process(Request request) {

        return new ViewModel("login");
    }
}
