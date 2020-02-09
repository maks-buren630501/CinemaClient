package com.example.cinemaclient.ui.presenter;

import android.os.AsyncTask;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.LoginActivity;

import java.io.IOException;
import java.net.InetAddress;

public class LoginPresenter {
    private User user;

    private LoginActivity view;

    private MyTask myTask;

    public LoginPresenter(LoginActivity loginActivity) {
        this.view = loginActivity;
    }

    /**
     * function to create user
     *
     * @return 1 if create success and -1 if was any error
     */
    public void createUser() throws InterruptedException {
        myTask = new MyTask();
        myTask.execute();
        Thread.sleep(1000);
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
            switch (user.getClientSocket().sendRequestLogin(login)){
                case 1:{
                    view.showRegisterActivity();
                    break;
                }
                case 2:{
                    user.setNickName(login);
                    view.showStartActivity();
                    break;
                }
            }
        }
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                User.getInstance("unlnow", "unknow", "unknow", "192.168.0.104", 8080);

            } catch (IOException e) {
                view.showError();
                e.printStackTrace();
            }
            return null;
        }

    }
}


