package com.huawei.kunpengimsystem.service;

import com.huawei.kunpengimsystem.entity.Participant;

public interface ParticipantService {
    Integer createParticipant(Participant participant);

    String getUserNameByConversationId(Integer conversationId, Integer userId);

    Integer getConversationIdByUserIdAndContactId(Integer userId, Integer contactId);
}
