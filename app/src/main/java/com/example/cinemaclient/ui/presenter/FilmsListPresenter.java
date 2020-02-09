package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.FilmsListActivity;

import java.io.IOException;
import java.util.ArrayList;

public class FilmsListPresenter {

    private User user;
    private FilmsListActivity view;


    public FilmsListPresenter(FilmsListActivity view) {
        this.view = view;
        this.user = User.getInstance();
    }

    public ArrayList<String> getListOfFilms(){
        try {
            return Session.getListStringOfParameters(this.user.getClientSocket().sendRequestToGetListOfFilms());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void selectFilm(String film) throws IOException {
        String sessions = user.getClientSocket().sendRequestToGetListOfSessions(film);
        view.showSessionsActivity(sessions);


    }
}
