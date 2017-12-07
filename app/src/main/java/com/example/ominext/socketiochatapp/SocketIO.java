package com.example.ominext.socketiochatapp;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Ominext on 12/7/2017.
 */

public class SocketIO {
    public static final String HOST = "http://192.168.1.153:1234";
    private static SocketIO instance;
    private Socket socket = IO.socket(HOST);

    public SocketIO() throws MalformedURLException, URISyntaxException {

    }

    public static SocketIO getInstance() throws MalformedURLException, URISyntaxException {
        if (instance == null) {
            instance = new SocketIO();
        }
        return instance;
    }

    public Socket getSocket() {
        return this.socket;
    }

}
