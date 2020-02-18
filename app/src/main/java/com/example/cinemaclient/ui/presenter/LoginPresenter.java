package com.example.cinemaclient.ui.presenter;

import android.os.AsyncTask;
import android.os.Trace;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.LoginActivity;

import java.io.IOException;
import java.net.InetAddress;

public class LoginPresenter {
    private User user;

    private LoginActivity view;

    private CreateUserRunnable createUserRunnable;

    private SendRequestRunnable sendRequestRunnable;

    public LoginPresenter(LoginActivity loginActivity) {
        this.view = loginActivity;
    }

    /**
     * function to create user
     *
     * @return 1 if create success and -1 if was any error
     */
    public void createUser() throws InterruptedException {
        createUserRunnable = new CreateUserRunnable(user);
        Thread thread = new Thread(createUserRunnable);
        //createUserRunnable.run();
        //Thread.sleep(1000);
        thread.run();
        thread.join();
        user = User.getInstance();
    }

    /**
     *
     * @param login
     * @throws IOException
     */
    public void clickPushButton(String login) throws IOException {
        if(login.length() == 0){
            view.showError();
        }
        else{
            sendRequestRunnable = new SendRequestRunnable(user,view,login);
            sendRequestRunnable.run();
        }
    }

    class CreateUserRunnable implements Runnable{
        private User user;
        public CreateUserRunnable(User user){
            this.user = user;
        }

        @Override
        public void run() {
            try {
                User.getInstance("unlnow", "unknow", "unknow", "192.168.43.10", 8080);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SendRequestRunnable implements Runnable{
        private User user;
        private LoginActivity view;
        private String login;
        public SendRequestRunnable(User user,LoginActivity view,String login){
            this.user = user;
            this.view = view;
            this.login = login;
        }

        @Override
        public void run() {
            try {
                switch (user.getClientSocket().sendRequestLogin(login)) {
                    case 1: {
                        view.showRegisterActivity();
                        break;
                    }
                    case 2: {
                        user.setNickName(login);
                        view.showStartActivity();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}









