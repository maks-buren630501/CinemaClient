package com.example.cinemaclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    Button loginButton = (Button)findViewById(R.id.loginButton);
    EditText loginEdit = (EditText)findViewById(R.id.lineToLogin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            ClientSocket.getInstance(InetAddress.getByName("127.0.0.1"),8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
    }

    public void setLoginButton(){
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String login = loginEdit.getText().toString();
                        if(login.length() == 0){
                            Toast.makeText(MainActivity.this,"please input login",Toast.LENGTH_SHORT);
                        }
                        else{
                            int result;
                            try {
                                result = ClientSocket.getInstance().sendRequestLogin(login);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }
        );

    }
}
