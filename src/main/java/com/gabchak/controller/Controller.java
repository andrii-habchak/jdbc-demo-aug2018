package com.gabchak.controller;

import com.gabchak.web.Requeast;
import com.gabchak.web.ViewModel;

public interface Controller {

    ViewModel process(Requeast requeast);

}
