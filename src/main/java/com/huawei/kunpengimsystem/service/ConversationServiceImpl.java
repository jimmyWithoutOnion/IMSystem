package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Conversation;
import com.huawei.kunpengimsystem.mapper.ConversationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ConversationService")
@Transactional
public class ConversationServiceImpl implements ConversationService{
    @Autowired
    private ConversationMapper conversationMapper;

    @Override
    public List<Conversation> getAllConversationByUserId(Integer userId) {
        return null;
    }

    @Override
    public Integer createConversation(Conversation conversation) {
        return conversationMapper.createConversation(conversation);
    }
}
