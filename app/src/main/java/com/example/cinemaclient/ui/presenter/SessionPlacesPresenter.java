package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.SessionPlacesActivity;

import java.io.IOException;

public class SessionPlacesPresenter {

    private SessionPlacesActivity view;
    private User user;

    public SessionPlacesPresenter(SessionPlacesActivity view) {
        this.view = view;
        this.user = User.getInstance();
    }

    public void selectPlace(int place) throws InterruptedException {
        SendRequestToAddUserSessionToDB sendRequestToAddUserSessionToDB = new SendRequestToAddUserSessionToDB(place,Integer.valueOf(view.getSessionNumber()),user);
        Thread thread = new Thread(sendRequestToAddUserSessionToDB);
        thread.run();
        thread.join();
        view.showStartActivity();
    }

    public class SendRequestToAddUserSessionToDB implements Runnable{
        User user;
        private int place;
        private int session;
        private int status;

        public SendRequestToAddUserSessionToDB(int place, int session, User user){
            this.place = place;
            this.session = session;
            this.user = user;
        }
        @Override
        public void run() {
            try {
                switch (user.getClientSocket().sendRequestToAddNewUserSession(this.session,this.place)){
                    case 1:{
                        status = 1;
                        break;
                    }
                    case 2:{
                        status = 2;
                        view.showError();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int getStatus(){
            return status;
        }
    }
}
