package com.game.gobang.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 项目名称：gobang-game-server
 * 类名称：WebSocketHandle
 * 类描述：Socket处理器
 * 创建人：yingx
 * 创建时间： 2019/12/5
 * 修改人：yingx
 * 修改时间： 2019/12/5
 * 修改备注：
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketHandle {

    private  static  int COUNT = 0;

    private static CopyOnWriteArraySet<WebSocketHandle> websocket = new CopyOnWriteArraySet<WebSocketHandle>();

    private Session session;
    /*
    建立连接的时候调用
     */
    @OnOpen
    public  void onOpen(Session session){
        this.session = session;
        websocket.add(this);
        addOnlineCount();
        System.out.println("当前在线人数："+COUNT);
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onclose(){
        websocket.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /*
    接收到客户端的信息
     */
    @OnMessage
    public  void shoudaoMessage(String message, Session session){
        System.out.println("server 收到的信息是：" +message);
        //群发消息
        for (WebSocketHandle item : websocket) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketHandle item : websocket) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return COUNT;
    }

    public static synchronized void addOnlineCount() {
        WebSocketHandle.COUNT++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketHandle.COUNT--;
    }
}
