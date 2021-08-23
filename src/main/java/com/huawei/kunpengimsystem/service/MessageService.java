package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getMessageByConversationIdWithLimit(Integer conversationId, Integer limit);

    int createMessage(Message message);

    void sendToUser(Message message);

    int createMockMessage(Message message);
}
