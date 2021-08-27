package com.huawei.kunpengimsystem.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        // 获取例如 wss://localhost/websocket/1 订阅地址
        // 中的最后一个用户 id 参数作为用户的标识,
        // 为实现发送信息给指定用户做准备

        // 获取订阅地址的最后一个/
        // 为了获取http://localhost:18080/api/websocket/${document.getElementById('conversationId').value}`中的id
        String uri = request.getURI().toString();
        String result = uri.substring(uri.indexOf("websocket") + 10);
        String uid = result.substring(0, result.indexOf("/"));
        return () -> uid;
    }
}
