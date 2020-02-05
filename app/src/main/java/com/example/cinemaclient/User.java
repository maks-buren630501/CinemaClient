package com.example.cinemaclient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class User {
    private static User instance = null;
    private ClientSocket clientSocket;
    private String nickName;
    private String lastName;
    private String firstName;
    private ArrayList<UserSession> listOfSessions;

    /**
     * default constructor without params
     */
    private User(){
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
    private User(String nickName,String lastName, String firstName, InetAddress address, int port){
        this.nickName = nickName;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * function for get object of this class
     * @param nickName of user
     * @param lastName of user
     * @param firstName of user
     * @param address ip address of server
     * @param port of server
     * @return object or link on this class
     * @throws IOException default exception
     */
    public static User getInstance(String nickName,String lastName, String firstName, InetAddress address, int port) throws IOException {
        if(instance == null){
            instance = new User(nickName,lastName,firstName,address,port);
        }
        return instance;
    }

    /**
     * function for get object of this class without params
     * @return object or link on this class
     */
    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    /**
     * set method for list of sessions
     * @param listOfSessions in array list format
     */
    public void setListOfSessions(ArrayList<UserSession> listOfSessions){
        this.listOfSessions.clear();
        this.listOfSessions = listOfSessions;
    }

    /**
     * method to add new session to list
     * @param session of user
     */
    public void addNewSession(UserSession session){
        this.listOfSessions.add(session);
    }

    /**
     * def setter
     * @param nickName of user
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * gef getter
     * @return of user
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * def setter
     * @param lastName of user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * def getter
     * @return last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * def setter
     * @param firstName of user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * def getter
     * @return first name of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * def getter
     * @return client socket
     */
    public ClientSocket getClientSocket() {
        return clientSocket;
    }
}
