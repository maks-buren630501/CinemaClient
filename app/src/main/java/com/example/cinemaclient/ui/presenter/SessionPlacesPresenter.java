package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.SessionPlacesActivity;

public class SessionPlacesPresenter {

    private SessionPlacesActivity view;
    private User user;

    public SessionPlacesPresenter(SessionPlacesActivity view) {
        this.view = view;
        this.user = User.getInstance();
    }
}
