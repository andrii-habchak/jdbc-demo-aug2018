package com.gabchak.controller;

import com.gabchak.web.Request;
import com.gabchak.web.ViewModel;

public interface Controller {

    ViewModel process(Request request);

}
