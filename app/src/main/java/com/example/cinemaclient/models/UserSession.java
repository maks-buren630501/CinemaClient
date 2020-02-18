package com.example.cinemaclient.models;

import java.util.ArrayList;

public class UserSession extends Session {
    private int hall;
    private int place;

    public UserSession(){

    }

    /**
     * Default constructor with params
     * @param filmTittle is name of film
     * @param date       of session
     * @param time       of session
     */
    public UserSession(String filmTittle, String date, String time, int hall, int place) {
        super();
        this.number = 0;
        this.filmTittle = filmTittle;
        this.date = date;
        this.time = time;
        this.hall = hall;
        this.place = place;
    }

    public int getPlace() {
        return place;
    }

    /**
     * function get string part of answer from server and make UserSession object from string
     * @param buffer with user session information
     * @return new object of UserSession class with params from string
     */
    @Override
    public UserSession parseFromString(String buffer){
        String film = "";
        String date = "";
        String time = "";
        String hall = "";
        String place = "";
        int fl = 0;
        for (int i = 0; i < buffer.length();i++) {
            if(fl == 0){
                if(buffer.charAt(i) != '/'){
                    film += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 1){
                if(buffer.charAt(i) != '/'){
                    date += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 2){
                if(buffer.charAt(i) != '/'){
                    time += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 3){
                if(buffer.charAt(i) != '/'){
                    hall += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 4){
                if(buffer.charAt(i) != '/'){
                    place += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 5){
                break;
            }
        }
        int numOfHall = Integer.valueOf(hall);
        int numOfPlace = Integer.valueOf(place);
        return new UserSession(film,date,time,numOfHall,numOfPlace);
    }

    public ArrayList<UserSession> getListOfUserSession(String request){
        ArrayList<UserSession> sessions = new ArrayList<UserSession>();
        ArrayList<String> sessionsString = getListStringOfParameters(request);
        for(String session:sessionsString){
            sessions.add(this.parseFromString(session));
        }
        return sessions;
    }

    public int getHall() {
        return hall;
    }
}
