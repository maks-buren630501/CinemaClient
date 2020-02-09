package com.example.cinemaclient.models;

import java.util.ArrayList;

public class Session {
    protected int number;
    protected String filmTittle;
    protected String date;
    protected String time;

    /**
     * void default constructor
     */
    public Session(){}

    /**
     * Default constructor with params
     * @param number of session
     * @param filmTittle is name of film
     * @param date of session
     * @param time of session
     */
    public Session(int number, String filmTittle, String date, String time){
        this.number = number;
        this.filmTittle = filmTittle;
        this.date = date;
        this.time = time;
    }

    /**
     * default getter
     * @return number of session
     */
    public int getNumber() {
        return number;
    }

    /**
     * default getter
     * @return name of film
     */
    public String getFilmTittle() {
        return filmTittle;
    }

    /**
     * default getter
     * @return date of session
     */
    public String getDate() {
        return date;
    }

    /**
     * default getter
     * @return time of session
     */
    public String getTime() {
        return time;
    }

    /**
     * function get string part of answer from server and make Session object from string
     * @param buffer with session information
     * @return new object of Session class with params from string
     */
    public Session parseFromString(String buffer){
        String number = "";
        String film = "";
        String date = "";
        String time = "";
        int fl = 0;
        for (int i = 0; i < buffer.length();i++) {
            if(fl == 0){
                if(buffer.charAt(i) != '/'){
                    number += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 1){
                if(buffer.charAt(i) != '/'){
                    film += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 2){
                if(buffer.charAt(i) != '/'){
                    date += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 3){
                if(buffer.charAt(i) != '/'){
                    time += buffer.charAt(i);
                    continue;
                }
                else {
                    fl++;
                    continue;
                }
            }
            if(fl == 4){
                break;

            }
        }
        int num = Integer.valueOf(number);
        return new Session(num,film,date,time);
    }

    /**
     * function to get list of string with description of session
     * @param request string of request
     * @return list of string with description of session
     */
    public  static ArrayList<String> getListStringOfParameters(String request){
        ArrayList<String> sessions = new ArrayList<String>();
        String temp = "";
        for(int i = 0; i < request.length(); i++){
            if(request.charAt(i) != '|'){
                temp += request.charAt(i);
                continue;
            }
            else{
                sessions.add(temp);
                temp = "";
                continue;
            }
        }
        return sessions;
    }

    /**
     * function to convert list string to list int
     * @param listOfString with different params
     * @return list of int
     */
    public static ArrayList<Integer> getListIntFromListString(ArrayList<String> listOfString){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for(String string:listOfString){
            arrayList.add(Integer.valueOf(string));
        }
        return arrayList;
    }


    public ArrayList<Session> getListOfSession(String request){
        ArrayList<Session> sessions = new ArrayList<Session>();
        ArrayList<String> sessionsString = getListStringOfParameters(request);
        for(String session:sessionsString){
            sessions.add(parseFromString(session));
        }
        return sessions;
    }
}
