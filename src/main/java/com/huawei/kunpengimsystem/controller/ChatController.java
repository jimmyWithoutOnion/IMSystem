package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.Conversation;

import com.huawei.kunpengimsystem.entity.Participant;
import com.huawei.kunpengimsystem.service.ConversationService;
import com.huawei.kunpengimsystem.service.ParticipantService;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource(name="ConversationService")
    private ConversationService conversationService;

    @Resource(name="ParticipantService")
    private ParticipantService participantService;

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

    @RequestMapping("/queryAllConversationByUserId")
    public Result queryAllConversationByUserId(Integer userId) {
        return null;
    }


    @RequestMapping("/queryMessageByConversationIdWithLimit")
    public Result queryMessageByConversationIdWithLimit(Integer conversationId, Integer limit) {
        return null;
    }
}
