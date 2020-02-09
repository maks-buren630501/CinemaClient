package com.example.cinemaclient.ui.presenter;

import com.example.cinemaclient.models.User;
import com.example.cinemaclient.ui.activity.RegisterActivity;

import java.io.IOException;

public class RegisterPresenter {
    private User user;
    private RegisterActivity view;

    public RegisterPresenter(RegisterActivity view){
        this.view = view;
        this.user = User.getInstance();
    }

    public void pushRegisterButton(String firstName, String lastName) throws IOException {
        if(firstName.length() == 0 || lastName.length() == 0){
            view.showErrorEmpty();
        }
        else{
            switch (this.user.getClientSocket().sendRequestToAddNewUser(lastName,firstName)){
                case 1:{
                    view.showStartActivity();
                    break;
                }
                case 2:{
                    view.showError();
                    break;
                }
            }
        }
    }
}
