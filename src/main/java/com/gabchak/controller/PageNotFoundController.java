package com.gabchak.controller;

import com.gabchak.web.Requeast;
import com.gabchak.web.ViewModel;

public class PageNotFoundController implements Controller {

    @Override
    public ViewModel process(Requeast requeast) {
        return ViewModel.of("404");
    }
}
