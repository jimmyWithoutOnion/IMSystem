package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Conversation;

import java.util.List;

public interface ConversationService {
    List<Conversation> getAllConversationByUserId(Integer userId);

    Integer createConversation(Conversation conversation);
}
