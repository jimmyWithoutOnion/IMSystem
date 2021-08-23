package com.huawei.kunpengimsystem.service.Impl;

import com.huawei.kunpengimsystem.entity.Message;
import com.huawei.kunpengimsystem.mapper.MessageMapper;
import com.huawei.kunpengimsystem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MessageService")
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

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

    @Override
    public void sendToUser(Message message) {
        // 底层会自动将第二个参数目的地址 /chat/contact 拼接为
        // /user/username/chat/contact，其中第二个参数 username 即为这里的第一个参数
        // username 也是前文中配置的 Principal 用户识别标志
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(message.getConversationId()),
                "/chat/contact",
                message
        );
    }

    @Override
    public int createMockMessage(Message message) {
        return messageMapper.createMockMessage(message);
    }
}
