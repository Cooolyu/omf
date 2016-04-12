package com.gmsz.om.web.webScoket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.gmsz.om.common.constant.StateDefine;

@ServerEndpoint(value = "/websocket/light")
public class LightServerEndpoint {
	
	private static final Logger sysLogger = Logger.getLogger(StateDefine.SYS_LOG);
	
    private static final Set<LightServerEndpoint> connections = new CopyOnWriteArraySet<>();
	
	private Session session;
	
	@OnOpen
	public void open(Session session) {
		this.session = session;
		try {
			connections.add(this);
		} catch (NumberFormatException e) {
		
			e.printStackTrace();
		}
		
	}
//	@OnError
//	public void error(Throwable t) {
//		sysLogger.info("=========web socket error ");
//	}
	@OnMessage
	public void inMessage(String message) {
		broadcast("received!");
	}
	
	@OnClose
	public void end() {
		connections.remove(this);
	}
	
	public static void broadcast(String msg) {
		sysLogger.info("*** WebSocket send broadcast message ***");
		for (LightServerEndpoint client : connections) {
            try {
                client.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                	sysLogger.info("*************close error");
                    e1.printStackTrace();
                }
            }
        }
    }
	
	@OnError
	public void onError(Throwable e){
		sysLogger.info("error message: " + e.getMessage());
	}

}
