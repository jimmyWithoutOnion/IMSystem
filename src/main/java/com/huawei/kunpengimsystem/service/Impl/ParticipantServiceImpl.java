package com.huawei.kunpengimsystem.service.Impl;

import com.huawei.kunpengimsystem.entity.Participant;
import com.huawei.kunpengimsystem.mapper.ParticipantMapper;
import com.huawei.kunpengimsystem.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ParticipantService")
@Transactional
public class ParticipantServiceImpl implements ParticipantService {
    @Autowired
    private ParticipantMapper participantMapper;

    @Override
    public Integer createParticipant(Participant participant) {
        return participantMapper.createParticipant(participant);
    }

    @Override
    public String getUserNameByConversationId(Integer conversationId, Integer userId) {
        return participantMapper.selectUserNameByConversationId(conversationId, userId);
    }

    @Override
    public Integer getConversationIdByUserIdAndContactId(Integer userId, Integer contactId) {
        return participantMapper.selectConversationIdByUserIdAndContactId(userId, contactId);
    }
}
