package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.FilmsListActivity;

import java.io.IOException;
import java.util.ArrayList;

public class FilmsListPresenter {

    private User user;
    private FilmsListActivity view;
    private SendRequestToGetListOfFilms sendRequestToGetListOfFilms;
    private SendRequestToGetFilmSessions sendRequestToGetFilmSessions;


    public FilmsListPresenter(FilmsListActivity view) {
        this.view = view;
        this.user = User.getInstance();
    }

    public ArrayList<String> getListOfFilms() throws InterruptedException {
        ArrayList<String> listOfFilms = new ArrayList<String>();
        sendRequestToGetListOfFilms = new SendRequestToGetListOfFilms(user,listOfFilms);
        Thread thread = new Thread(sendRequestToGetListOfFilms);
        thread.run();
        thread.join();
        return sendRequestToGetListOfFilms.getListOfFilm();
    }

    public void selectFilm(String film) throws IOException {
        sendRequestToGetFilmSessions = new SendRequestToGetFilmSessions(view,user,film);
        sendRequestToGetFilmSessions.run();

    }

    public class SendRequestToGetListOfFilms implements Runnable{
        private User user;
        private ArrayList<String> listOfFilms;

        public SendRequestToGetListOfFilms(User user, ArrayList listOfFilms){
            this.listOfFilms = listOfFilms;
            this.user = user;
        }

        @Override
        public void run() {
            try {
                listOfFilms = Session.getListStringOfParameters(this.user.getClientSocket().sendRequestToGetListOfFilms());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public ArrayList<String> getListOfFilm(){
            return this.listOfFilms;
        }
    }

    public class SendRequestToGetFilmSessions implements Runnable{

        private FilmsListActivity view;
        private User user;
        private String film;

        public SendRequestToGetFilmSessions(FilmsListActivity view, User user, String film){
            this.view = view;
            this.user = user;
            this.film = film;
        }

        @Override
        public void run() {
            String sessions = null;
            try {
                sessions = user.getClientSocket().sendRequestToGetListOfSessions(film);
            } catch (IOException e) {
                e.printStackTrace();
            }
            view.showSessionsActivity(sessions);
        }
    }
}
