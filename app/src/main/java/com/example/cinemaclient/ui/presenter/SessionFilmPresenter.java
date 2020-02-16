package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.SessionFilmActivity;

import java.io.IOException;
import java.util.ArrayList;

public class SessionFilmPresenter {
    private SessionFilmActivity view;
    private User user;
    private SendRequestToGetPlaces sendRequestToGetPlaces;


    public SessionFilmPresenter(SessionFilmActivity view) {
        this.view = view;
        this.user = User.getInstance();
    }

    public ArrayList<Session> getSessionList(String stringExtra) {
        Session session = new Session();
        return session.getListOfSession(stringExtra);
    }

    public void selectSession(int number) throws IOException, InterruptedException {
        sendRequestToGetPlaces = new SendRequestToGetPlaces(user,number);
        Thread thread = new Thread(sendRequestToGetPlaces);
        thread.join();
        String hall = sendRequestToGetPlaces.getHall();
        String freePlacesList = sendRequestToGetPlaces.getFreePlacesList();
        view.showSessionPlacesActivity(hall,freePlacesList,number);
    }

    public class SendRequestToGetPlaces implements Runnable{

        int number;
        private User user;
        private String hall;
        private String freePlacesList;

        public SendRequestToGetPlaces(User user,int number){
            this.user = user;
            this.number = number;
        }

        @Override
        public void run() {

            try {
                hall = user.getClientSocket().sendRequestToGetHall(number);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                freePlacesList = user.getClientSocket().sendRequestToGetFreePlaces(number);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        public String getFreePlacesList(){
            return this.freePlacesList;
        }

        public String getHall(){
            return this.hall;
        }
    }
}
