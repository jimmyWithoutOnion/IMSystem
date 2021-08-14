package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Message;
import com.huawei.kunpengimsystem.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MessageService")
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessageByConversationIdWithLimit(Integer conversationId, Integer limit) {
        return messageMapper.selectMessageByConversationIdWithNumberLimit(conversationId, limit);
    }

    @Override
    public int createMessage(Message message) {
        return messageMapper.createMessage(message);
    }
}
