package com.huawei.kunpengimsystem.controller;

import com.huawei.kunpengimsystem.entity.*;

import com.huawei.kunpengimsystem.service.*;
import com.huawei.kunpengimsystem.utils.NativeUtil;
import com.huawei.kunpengimsystem.utils.Result;
import com.huawei.kunpengimsystem.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Resource(name="AttachmentService")
    private AttachmentService attachmentService;

    @Value("${file.upload.path}")
    private String path;

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
    // 这里的 @MessageMapping 可以当成 @RequestMapping,
    // 当有信息 (sendMsg 方法中的 messageEntity 参数即为客服端发送的信息实体)
    // 发送到 /sendMsg 时会在这里进行处理
    @MessageMapping("/sendMsg")
    public Result sendMsg(Message message) {
        messageService.sendToUser(message);
        Integer messageId = messageService.createMessage(message);
        if (!message.getMessageType().equals("text")) {
            // 创建attachment入库
            Attachment attachment = new Attachment();
            attachment.setMessageId(message.getId());
            String webResourcePath = message.getMessageContext();
            String filePath = webResourcePath.substring(webResourcePath.lastIndexOf("/") + 1);
            attachment.setFileAddress(path + filePath);
            // 添加crc校验码
            NativeUtil nativeUtil = new NativeUtil();
            String crcCode = nativeUtil.getCrc32Digest(path + filePath);
            attachment.setFileCheckCode(crcCode);
            attachmentService.createAttachment(attachment);
        }
        return ResultUtil.success(messageId);
    }

    @RequestMapping("uploadFile")
    public Result uploadFile(HttpServletRequest request) throws IOException {
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        List<String> destList = new ArrayList<>();
        if (files.size() == 0) {
            return ResultUtil.fail("file not found");
        }

        for (MultipartFile file: files) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String fileName = String.valueOf(timestamp.getTime());
            // 添加后缀
            String name = file.getOriginalFilename();
            if (name == null) {
                return ResultUtil.fail("illegal file");
            }
            int index = name.lastIndexOf(".");
            if (index == -1) {
                return ResultUtil.fail("illegal file");
            }
            String fileExt = name.substring(index);
            // 拼接文件名
            String filePath = path + fileName + fileExt;

            File dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());
            destList.add(fileName + fileExt);
        }

        return ResultUtil.success(destList);
    }

    @CrossOrigin
    @RequestMapping("/createMockMessage")
    public Result createMockMessage(Integer conversationId, Integer senderId, String messageType, String messageContext, String createTime) {
        Message message = new Message();
        message.setConversationId(conversationId);
        message.setSenderId(senderId);
        message.setMessageType(messageType);
        message.setMessageContext(messageContext);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        ParsePosition pos = new ParsePosition(0);
        Date date = simpleDateFormat.parse(createTime, pos);
        message.setCreateTime(date);
        messageService.createMockMessage(message);
        return ResultUtil.success(message.getId());
    }
}
