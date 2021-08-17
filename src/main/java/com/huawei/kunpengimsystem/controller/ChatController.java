package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.Conversation;

import com.huawei.kunpengimsystem.entity.Message;
import com.huawei.kunpengimsystem.entity.Participant;
import com.huawei.kunpengimsystem.entity.User;
import com.huawei.kunpengimsystem.service.ConversationService;
import com.huawei.kunpengimsystem.service.MessageService;
import com.huawei.kunpengimsystem.service.ParticipantService;
import com.huawei.kunpengimsystem.service.UserService;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource(name = "UserService")
    private UserService userService;

    @Resource(name="ConversationService")
    private ConversationService conversationService;

    @Resource(name="ParticipantService")
    private ParticipantService participantService;

    @Resource(name="MessageService")
    private MessageService messageService;

    // 创建对话
    @RequestMapping("/createConversation")
    public Result createConversation(Integer userId, String title, Integer contactId) {
        // 判断是否已经存在conversationId
        Integer conversationId = participantService.getConversationIdByUserIdAndContactId(userId, contactId);

        if (conversationId != null) {
            return ResultUtil.success(conversationId);
        } else {
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

            return ResultUtil.success(conversation.getId());
        }
    }

    // 根据参与者的用户id查询所有对话
    @RequestMapping("/queryAllConversationByParticipantUserId")
    public Result queryAllConversationByParticipantUserId(Integer userId) {
        List<HashMap> resultList = new ArrayList<>();
        List<Conversation> conversationList = conversationService.getAllConversationByParticipantUserId(userId);

        for (Conversation conversation: conversationList) {
            HashMap<String, String> map = new HashMap<>();
            String userName = participantService.getUserNameByConversationId(conversation.getId(), userId);
            map.put("title", userName);
            map.put("id", Integer.toString(conversation.getId()));
            List<Message> messageList = messageService.getMessageByConversationIdWithLimit(conversation.getId(), 1);
            if (messageList.size() > 0) {
                map.put("messageContext", messageList.get(0).getMessageContext());
            } else {
                map.put("messageContext", "");
            }
            resultList.add(map);
        }
        return ResultUtil.success(resultList);
    }

    // 根据对话id查询按时间倒排消息，limit是消息的条数
    @RequestMapping("/queryMessageByConversationIdWithLimit")
    public Result queryMessageByConversationIdWithLimit(Integer conversationId, Integer limit) {
        List<Message> messageList = messageService.getMessageByConversationIdWithLimit(conversationId, limit);
        List<HashMap> resultMapList = new ArrayList<>();
        for (Message message: messageList) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", message.getMessageType());
            User user = userService.getInformationById(message.getSenderId());
            map.put("name", user.getName());
            // 转换时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("time", simpleDateFormat.format(message.getCreateTime()));
            map.put("content", message.getMessageContext());
            map.put("senderId", String.valueOf(message.getSenderId()));
            map.put("messageType", message.getMessageType());
            resultMapList.add(map);
        }
        return ResultUtil.success(resultMapList);
    }

    // 发送消息
//    @RequestMapping("/createMessage")
//    public Result createMessage(Integer conversationId, Integer senderId, String messageType, String messageContext) {
//        Message message = new Message();
//        message.setConversationId(conversationId);
//        message.setSenderId(senderId);
//        message.setMessageType(messageType);
//        message.setMessageContext(messageContext);
//        messageService.createMessage(message);
//        return ResultUtil.success(null);
//    }

    // 这里的 @MessageMapping 可以当成 @RequestMapping,
    // 当有信息 (sendMsg 方法中的 messageEntity 参数即为客服端发送的信息实体)
    // 发送到 /sendMsg 时会在这里进行处理
    @MessageMapping("/sendMsg")
    public Result sendMsg(Message message) {
        messageService.sendToUser(message);
        Integer messageId = messageService.createMessage(message);
        return ResultUtil.success(messageId);
    }

}
