package com.huawei.kunpengimsystem.service.Impl;

import com.huawei.kunpengimsystem.entity.Conversation;
import com.huawei.kunpengimsystem.mapper.ConversationMapper;
import com.huawei.kunpengimsystem.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ConversationService")
@Transactional
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    private ConversationMapper conversationMapper;

    @Override
    public List<Conversation> getAllConversationByParticipantUserId(Integer userId) {
        return conversationMapper.selectAllConversationByParticipantUserId(userId);
    }

    @Override
    public Integer createConversation(Conversation conversation) {
        return conversationMapper.createConversation(conversation);
    }
}
