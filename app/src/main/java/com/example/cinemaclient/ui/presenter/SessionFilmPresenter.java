package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.SessionFilmActivity;

import java.io.IOException;
import java.util.ArrayList;

public class SessionFilmPresenter {
    private SessionFilmActivity view;
    private User user;


    public SessionFilmPresenter(SessionFilmActivity view) {
        this.view = view;
        this.user = User.getInstance();
    }

    public ArrayList<Session> getSessionList(String stringExtra) {
        Session session = new Session();
        return session.getListOfSession(stringExtra);
    }

    public void selectSession(int number) throws IOException {
        String hall = user.getClientSocket().sendRequestToGetHall(number);
        String FreePlacesList = user.getClientSocket().sendRequestToGetFreePlaces(number);
        
    }
}
