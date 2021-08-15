package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.Conversation;

import com.huawei.kunpengimsystem.entity.Message;
import com.huawei.kunpengimsystem.entity.Participant;
import com.huawei.kunpengimsystem.service.ConversationService;
import com.huawei.kunpengimsystem.service.MessageService;
import com.huawei.kunpengimsystem.service.ParticipantService;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource(name="ConversationService")
    private ConversationService conversationService;

    @Resource(name="ParticipantService")
    private ParticipantService participantService;

    @Resource(name="MessageService")
    private MessageService messageService;

    // 创建对话
    @RequestMapping("/createConversation")
    public Result createConversation(Integer userId, String title, Integer contactId) {
        Conversation conversation = new Conversation();
        conversation.setTitle(title);
        conversation.setCreatorId(userId);
        conversationService.createConversation(conversation);

        Participant participant = new Participant();
        participant.setConversationId(conversation.getId());
        participant.setUserId(userId);
        participant.setType("single");
        participantService.createParticipant(participant);
        participant.setUserId(contactId);
        participantService.createParticipant(participant);

        return ResultUtil.success(null);
    }

    // 根据参与者的用户id查询所有对话
    @RequestMapping("/queryAllConversationByParticipantUserId")
    public Result queryAllConversationByParticipantUserId(Integer userId) {
        List<Conversation> conversationList = conversationService.getAllConversationByParticipantUserId(userId);
        return ResultUtil.success(conversationList);
    }

    // 根据对话id查询按时间倒排消息，limit是消息的条数
    @RequestMapping("/queryMessageByConversationIdWithLimit")
    public Result queryMessageByConversationIdWithLimit(Integer conversationId, Integer limit) {
        List<Message> messageList = messageService.getMessageByConversationIdWithLimit(conversationId, limit);
        return ResultUtil.success(messageList);
    }

    // 发送消息
    @RequestMapping("/createMessage")
    public Result createMessage(Integer conversationId, Integer senderId, String messageType, String messageContext) {
        Message message = new Message();
        message.setConversationId(conversationId);
        message.setSenderId(senderId);
        message.setMessageType(messageType);
        message.setMessageContext(messageContext);
        messageService.createMessage(message);
        return ResultUtil.success(null);
    }

    // 这里的 @MessageMapping 可以当成 @RequestMapping,
    // 当有信息 (sendMsg 方法中的 messageEntity 参数即为客服端发送的信息实体)
    // 发送到 /sendMsg 时会在这里进行处理
    @MessageMapping("/sendMsg")
    public void sendMsg(Message message) {
        System.out.print("********message:" + message);
        messageService.sendToUser(message);
    }

}
