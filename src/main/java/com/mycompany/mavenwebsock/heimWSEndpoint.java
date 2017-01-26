/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.s
 */
package com.mycompany.mavenwebsock;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * webapp Wismar
 * 
 * @author duemchen
 * 
 * Einloggen
 * Hash in localstorage
 * Neueinloggen dann automatisch 
 * gewolltes logout löscht hashwert
 * 
 * Hole meine letzte Buchungen: 
 * Status anzeigen
 * erlaubte Knöpfe steuern
 * 
 * Drücke Knöpfe
 * Buchungen direkt absetzen
 * Hole meine letzte Buchungen: 
 * Status aktualisieren
 * erlaubte Knöpfe steuern
 * 
 * Abfrage aller Aufträge
 * Filterung nach Position
 * 
 * Auftragsanfang
 * Liste anzeigen und Auftrag auswählen 
 * Auftrag anzeigen und bestätigen 
 *  
 * OfflineFunktionen
 * 
 * Iphone test
 * WinPhone test
 * 
 * 
 * 
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
                     Thread.sleep(1500);
                    for (int i = 0; i < 30; i++) {
                        Thread.sleep(30);
                        s.getBasicRemote().sendText(i + ". Message from server");
                        System.err.println("i=" + i);
                    }
                    s.getBasicRemote().sendText("ready.");
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
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(heimWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
