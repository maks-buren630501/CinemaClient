package com.example.cinemaclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

    Socket clientSocket = null;
    private BufferedReader in;
    private BufferedWriter out;
    private String answer;

    /**
     * close constructor
     * @param address ip4 address
     * @param port port of server application
     * @throws IOException
     */
    public ClientSocket(InetAddress address, int port) throws IOException {
        clientSocket = new Socket(address,port);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }

    /**
     * function send request free of busy nickname
     * @param login login which user input
     * @return 1 if nickname is free and 2 if is busy
     * @throws IOException
     */
    public int sendRequestLogin(String login) throws IOException {
        out.write(login + "\0");
        out.flush();
        answer = in.readLine();
        if(answer.contains("nickNameIsFree")){
            return 1;
        }
        else {
            return 2;
        }
    }

    /**
     * function send request to add new user to data base with some last and first name
     * @param lastName is last name of user
     * @param firsName is first name of user
     * @return 1 if add to data base was successfully and 2 if was some error
     * @throws IOException
     */
    public int sendRequestToAddNewUser(String lastName, String firsName) throws IOException {
        out.write("addNewUserToDB|"+lastName+"|"+firsName+"|\0");
        out.flush();
        answer = in.readLine();
        if(answer.contains("successfullyAddingToDB")){
            return 1;
        }
        else{
            return 2;
        }
    }

    /**
     * function for send request to get list of films
     * @return string list of films
     * @throws IOException
     */
    public String sendRequestToGetListOfFilms() throws IOException {
        out.write("getListOfFilm\0");
        out.flush();
        answer = in.readLine();
        return answer;
    }

    /**
     * function for send request to get list of sessions by film tittle
     * @param tittle tittle of chose film
     * @return string of sessions information
     * @throws IOException
     */
    public String sendRequestToGetListOfSessions(String tittle) throws IOException{
        out.write("getSessionsByFilmTittle|"+tittle+"|\0");
        out.flush();
        answer = in.readLine();
        return answer;
    }

    /**
     * function for send request to get number of hall by number of session
     * @param numOfSession number of session
     * @return string number of hall
     * @throws IOException
     */
    public String sendRequestToGetHall(int numOfSession) throws IOException{
        out.write("getHallByNumOfSession|"+Integer.toString(numOfSession)+"|\0");
        out.flush();
        answer = in.readLine();
        return answer;
    }

    /**
     * function for send request to get free places string by number of session
     * @param numOfSession number of session
     * @return string free places
     * @throws IOException
     */
    public String sendRequestToGetFreePlaces(int numOfSession) throws IOException{
        out.write("getFreePlacesByNumOfSession|"+Integer.toString(numOfSession)+"|\0");
        out.flush();
        answer = in.readLine();
        return answer;
    }

    /**
     * func to add new user session to data base
     * @param numOfSession num of chose session
     * @param place chose place
     * @return 1 if user session successfully added to data base and 2 if was some error
     * @throws IOException
     */
    public int sendRequestToAddNewUserSession(int numOfSession, int place) throws IOException {
        out.write("addUserSessionToDB|"+Integer.toString(numOfSession)+"|"+Integer.toString(place)+"|\0");
        out.flush();
        answer = in.readLine();
        if(answer.contains("successfullyAddingToDB")){
            return 1;
        }
        else{
            return 2;
        }
    }

    /**
     * function to get from server information about all user sessions
     * @return
     * @throws IOException
     */
    public String sendRequestToGetSessionsInformation() throws IOException{
        out.write("getMySessionsInformation|0");
        out.flush();
        answer = in.readLine();
        return answer;
    }

}
