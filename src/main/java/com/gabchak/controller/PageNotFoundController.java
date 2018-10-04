package com.gabchak.controller;

import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public class PageNotFoundController implements Controller {

    @Override
    public ViewModel process(Request request) {
        return ViewModel.of("404");
    }
}
