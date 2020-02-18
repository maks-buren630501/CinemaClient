package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.models.UserSession;
import com.example.cinemaclient.ui.activity.UserSessionsActivity;

import java.io.IOException;
import java.util.ArrayList;

public class UserSessionPresenter {
    private UserSessionsActivity view;
    private User user;

    public UserSessionPresenter(UserSessionsActivity view){
        this.view = view;
        user = User.getInstance();
    }

    public ArrayList<UserSession> getUserSessionsList() throws InterruptedException {
        ArrayList<UserSession> userSessions = new ArrayList<>();
        SendRequestToGetUsersSessionsInformation sendRequestToGetUsersSessionsInformation = new SendRequestToGetUsersSessionsInformation(user);
        Thread thread = new Thread(sendRequestToGetUsersSessionsInformation);
        thread.run();
        thread.join();
        userSessions = sendRequestToGetUsersSessionsInformation.getUserSessions();
        return userSessions;
    }

    public class SendRequestToGetUsersSessionsInformation implements Runnable{
        private User user;
        private ArrayList<UserSession> userSessions;

        public SendRequestToGetUsersSessionsInformation(User user){
            this.user = user;
        }

        @Override
        public void run() {
            try {
                UserSession userSession = new UserSession();
                this.userSessions = userSession.getListOfUserSession(user.getClientSocket().sendRequestToGetSessionsInformation());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public ArrayList<UserSession> getUserSessions() {
            return userSessions;
        }
    }
}
