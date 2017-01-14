/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebsock;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author duemchen
 */
@ServerEndpoint("/endpoint")
public class heimWSEndpoint {

    @OnOpen
    public void onOpen(Session sess){
        System.out.println("open sess "+sess);
    }

    @OnClose
    public void onClose(Session sess){
        System.out.println("close sess "+sess);
    }

    
    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("onMessage "+message);
        return null;
    }
    
}