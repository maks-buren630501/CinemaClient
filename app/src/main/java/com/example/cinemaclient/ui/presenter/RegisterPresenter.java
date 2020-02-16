package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.LoginActivity;
import com.example.cinemaclient.ui.activity.RegisterActivity;

import java.io.IOException;

public class RegisterPresenter {
    private User user;
    private RegisterActivity view;
    private SendRequestRunnable sendRequestRunnable;

    public RegisterPresenter(RegisterActivity view){
        this.view = view;
        this.user = User.getInstance();
    }

    public void pushRegisterButton(String firstName, String lastName) throws IOException {
        if(firstName.length() == 0 || lastName.length() == 0){
            view.showErrorEmpty();
        }
        else{
            sendRequestRunnable = new SendRequestRunnable(user,view,firstName,lastName);
            sendRequestRunnable.run();
        }
    }

    public class SendRequestRunnable implements Runnable{
        private User user;
        private RegisterActivity view;
        private String firstName;
        private String lastName;

        public SendRequestRunnable(User user,RegisterActivity view,String firstName, String lastName){
            this.user = user;
            this.view = view;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public void run() {
            try {
                switch (this.user.getClientSocket().sendRequestToAddNewUser(lastName, firstName)) {
                    case 1: {
                        view.showStartActivity();
                        break;
                    }
                    case 2: {
                        view.showError();
                        break;
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
