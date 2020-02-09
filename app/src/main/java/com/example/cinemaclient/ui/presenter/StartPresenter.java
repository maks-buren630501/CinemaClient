package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.StartActivity;

public class StartPresenter {
    private User user;
    private StartActivity view;


    public StartPresenter(StartActivity view) {
        this.view = view;
        user = User.getInstance();
    }

    public void clickButtonAddNewSession(){

    }

    public void clickButtonShowListOfSessions(){

    }
}
