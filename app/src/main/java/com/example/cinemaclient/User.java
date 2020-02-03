package com.example.cinemaclient;

import java.util.ArrayList;

public class User {
    private String nickName;
    private String lastName;
    private String firstName;
    private ArrayList<Session> listOfSessions;

    /**
     * default constructor without params
     */
    public User(){
        this.firstName = "unknow";
        this.lastName = "unknow";
        this.nickName = "unknow";
    }

    /**
     * default constructor with params
     * @param nickName of user
     * @param lastName of user
     * @param firstName of user
     */
    public User(String nickName,String lastName, String firstName){
        this.nickName = nickName;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * set method for list of sessions
     * @param listOfSessions
     */
    public void setListOfSessions(ArrayList<Session> listOfSessions){
        this.listOfSessions.clear();
        this.listOfSessions = listOfSessions;
    }

    /**
     * method to add new session to list
     * @param session
     */
    public void addNewSession(Session session){
        this.listOfSessions.add(session);
    }
}
