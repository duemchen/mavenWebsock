/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.s
 */
package com.mycompany.mavenwebsock;

import java.io.IOException;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
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
@Stateless
public class heimWSEndpoint {

    @Resource
    ManagedExecutorService mes;

    @OnOpen
    public void onOpen(Session sess) {
        System.out.println("open sess " + sess);
        final Session s = sess;
        mes.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        Thread.sleep(100);
                        s.getBasicRemote().sendText(i + ". Message from server");
                        System.out.println("i=" + i);
                    }
                    s.getBasicRemote().sendText("byebye.");
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @OnClose
    public void onClose(Session sess) {
        System.out.println("close sess " + sess);
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("onMessage " + message);
        return null;
    }

}
